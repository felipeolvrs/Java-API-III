package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.models.Emprestimo;
import com.example.api.domain.models.Multa;
import com.example.api.domain.services.MultaServiceInterface;
import com.example.api.exception.RecursoNaoEncontradoException;
import com.example.api.infrastructure.repository.springdata.EmprestimoRepositorySD;
import com.example.api.infrastructure.repository.springdata.MultaRepositorySD;
import com.example.api.infrastructure.repository.springdata.UsuarioRepositorySD;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MultaService extends BaseService<Multa, Long> implements MultaServiceInterface {

    private final MultaRepositorySD multasRepository;
    private final EmprestimoRepositorySD emprestimoRepository;
    private final UsuarioRepositorySD usuarioRepository;

    public MultaService (
            MultaRepositorySD multasRepository,
            EmprestimoRepositorySD emprestimoRepository,
            UsuarioRepositorySD usuarioRepository
    ) {
            super(multasRepository, "Multa");
            this.multasRepository = multasRepository;
            this.emprestimoRepository = emprestimoRepository;
            this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Multa> consultarMultasPorUsuario(Long usuarioId) {
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        return multasRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    public Multa registrarMulta(Long emprestimoId, long diasAtraso, double valorDiario) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado."));


        double valorTotalMulta = diasAtraso * valorDiario;

        Multa novaMulta = new Multa();
        novaMulta.setEmprestimo(emprestimo);
        novaMulta.setUsuario(emprestimo.getUsuario());
        novaMulta.setValor(BigDecimal.valueOf(valorTotalMulta));
        novaMulta.setDataMulta(LocalDate.now().atStartOfDay());
        novaMulta.setStatusPagamento(StatusPagamento.PENDENTE);

        return multasRepository.save(novaMulta);
    }
}