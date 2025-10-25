package com.example.api.domain.services;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.models.Usuario;

public interface UsuarioServiceInterface extends BaseServiceInterface<Usuario, Long> {

    boolean temDivida(Long usuarioId);
    void adicionarDivida(Long usuarioId, double valor);

}
