package com.example.api.domain.repositories;

import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.models.Multa;
import com.example.api.domain.models.Usuario;

import java.util.List;

public interface MultaRepositoryInterface {

    List<Multa> findByUsuarioId(Long usuarioId);

    Double calcularTotalDividas(Long usuarioId);

    boolean existsByUsuarioAndStatusPagamento(Usuario usuario, StatusPagamento status);
}
