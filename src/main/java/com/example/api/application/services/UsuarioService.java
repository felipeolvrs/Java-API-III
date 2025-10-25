package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.models.Usuario;
import com.example.api.domain.repositories.UsuarioRepositoryInterface;
import com.example.api.domain.services.UsuarioServiceInterface;
import com.example.api.infrastructure.repository.springdata.UsuarioRepositorySD;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario,  Long> implements UsuarioServiceInterface {

    private UsuarioRepositorySD usuarioRepository;

    public UsuarioService(UsuarioRepositorySD usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean temDivida(Long usuarioId) {
        return false;
    }

    @Override
    public void adicionarDivida(Long usuarioId, double valor) {

    }

    //TODO: Implementar os serviços da interface
}
