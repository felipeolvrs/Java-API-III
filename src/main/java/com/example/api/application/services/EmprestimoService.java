package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.enums.StatusEmprestimo;
import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.enums.StatusReserva;
import com.example.api.domain.models.Emprestimo;
import com.example.api.domain.models.Item;
import com.example.api.domain.models.Multa;
import com.example.api.domain.models.Reserva;
import com.example.api.domain.models.Usuario;
import com.example.api.domain.services.EmprestimoServiceInterface;
import com.example.api.exception.EstoqueInsuficienteException;
import com.example.api.exception.RegraNegocioException;
import com.example.api.exception.RecursoNaoEncontradoException;
import com.example.api.exception.UsuarioComDividaException;
import com.example.api.infrastructure.repository.springdata.EmprestimoRepositorySD;
import com.example.api.infrastructure.repository.springdata.ItemRepositorySD;
import com.example.api.infrastructure.repository.springdata.MultaRepositorySD;
import com.example.api.infrastructure.repository.springdata.ReservaRepositorySD;
import com.example.api.infrastructure.repository.springdata.UsuarioRepositorySD;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class EmprestimoService extends BaseService<Emprestimo, Long> implements EmprestimoServiceInterface {

    private final EmprestimoRepositorySD emprestimoRepository;
    private final ItemRepositorySD itemRepository;
    private final UsuarioRepositorySD usuarioRepository;
    private final MultaRepositorySD multaRepository;
    private final ReservaRepositorySD reservaRepository;

    private static final int MAX_RENOVACOES = 2;
    private static final BigDecimal MULTA_DIARIA = BigDecimal.valueOf(5.0);

    public EmprestimoService(
            EmprestimoRepositorySD emprestimoRepository,
            ItemRepositorySD itemRepository,
            UsuarioRepositorySD usuarioRepository,
            MultaRepositorySD multaRepository,
            ReservaRepositorySD reservaRepository
    ) {
        super(emprestimoRepository, "Emprestimo");
        this.emprestimoRepository = emprestimoRepository;
        this.itemRepository = itemRepository;
        this.usuarioRepository = usuarioRepository;
        this.multaRepository = multaRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    @Transactional
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        Item item = itemRepository.findById(emprestimo.getItem().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado"));

        boolean usuarioDevendo = multaRepository.existsByUsuarioAndStatusPagamento(
                usuario,
                StatusPagamento.PENDENTE
        );

        if (usuarioDevendo)
            throw new UsuarioComDividaException("Usuário possui dívidas e não pode realizar empréstimos.");

        Optional<Reserva> reservaAtivaOpt = reservaRepository.findByItemAndStatus(
                item,
                StatusReserva.ATIVA
        );

        if (reservaAtivaOpt.isPresent()) {
            Reserva reservaAtiva = reservaAtivaOpt.get();

            if (!reservaAtiva.getUsuario().equals(usuario))
                throw new RegraNegocioException("Este item está reservado para outro usuário.");

            reservaAtiva.setStatus(StatusReserva.CONCLUIDA);
            reservaRepository.save(reservaAtiva);
        }

        if (item.getEstoque() <= 0)
            throw new EstoqueInsuficienteException("Item sem estoque disponível.");

        item.setEstoque(item.getEstoque() - 1);
        itemRepository.save(item);

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(7));
        emprestimo.setStatus(StatusEmprestimo.ATIVO);
        emprestimo.setNumRenovacoes(0);

        return emprestimoRepository.save(emprestimo);
    }

    @Override
    @Transactional
    public Emprestimo renovarEmprestimo(Long Id) {
        Emprestimo emprestimo = emprestimoRepository.findById(Id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado."));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO)
            throw new RegraNegocioException("Não é possível renovar um empréstimo que não está ativo.");

        if (emprestimo.getNumRenovacoes() >= MAX_RENOVACOES)
            throw new RegraNegocioException("Limite máximo de renovações atingido.");

        emprestimo.setNumRenovacoes(emprestimo.getNumRenovacoes() + 1);
        emprestimo.setDataPrevistaDevolucao(emprestimo.getDataPrevistaDevolucao().plusDays(7));

        return emprestimoRepository.save(emprestimo);
    }

    @Override
    @Transactional
    public Emprestimo devolverEmprestimo(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado."));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO)
            throw new RegraNegocioException("Este empréstimo já foi devolvido ou está concluído.");

        Item item = emprestimo.getItem();
        item.setEstoque(item.getEstoque() + 1);
        itemRepository.save(item);

        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);

        long diasAtraso = Math.max(0, ChronoUnit.DAYS.between(
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataDevolucaoReal()
        ));

        if (diasAtraso > 0) {
            BigDecimal valorMulta = MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso));

            Multa multa = new Multa();
                multa.setUsuario(emprestimo.getUsuario());
                multa.setEmprestimo(emprestimo);
                multa.setValor(valorMulta);
                multa.setStatusPagamento(StatusPagamento.PENDENTE);
                multa.setDataMulta(LocalDate.now().atStartOfDay());

            multaRepository.save(multa);
        }

        return emprestimoRepository.save(emprestimo);
    }
}