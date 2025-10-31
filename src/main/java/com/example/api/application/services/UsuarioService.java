package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.models.Multa;
import com.example.api.domain.models.Usuario;
import com.example.api.domain.services.UsuarioServiceInterface;
import com.example.api.infrastructure.repository.springdata.MultaRepositorySD;
import com.example.api.infrastructure.repository.springdata.UsuarioRepositorySD;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class UsuarioService extends BaseService<Usuario,  Long> implements UsuarioServiceInterface {

    private final MultaRepositorySD multaRepository;

    public UsuarioService(UsuarioRepositorySD usuarioRepository, MultaRepositorySD multaRepository) {
        super(usuarioRepository, "Usuario");
        this.multaRepository = multaRepository;
    }

    @Override
    public boolean temDivida(Long usuarioId) {
        if (usuarioId == null )
            throw new IllegalArgumentException("O Id do usuário não pode ser nulo");

        Usuario usuario = buscarPorId(usuarioId);

        return multaRepository.existsByUsuarioAndStatusPagamento(usuario, StatusPagamento.PENDENTE);
    }

    @Override
    public void adicionarDivida(Long usuarioId, double valor) {
        Usuario usuario = buscarPorId(usuarioId);

        Multa novaMulta = new Multa();
        novaMulta.setUsuario(usuario);
        novaMulta.setValor(BigDecimal.valueOf(valor));
        novaMulta.setStatusPagamento(StatusPagamento.PENDENTE);

        multaRepository.save(novaMulta);
    }
}