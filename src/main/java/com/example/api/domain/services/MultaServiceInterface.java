package com.example.api.domain.services;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.models.Multa;
import com.example.api.domain.models.Usuario;

import java.util.List;

public interface MultaServiceInterface extends BaseServiceInterface<Multa, Long> {

    List<Multa> consultarMultasPorUsuario(Long usuarioId);

    Multa registrarMulta(Long emprestimoId, long diasAtraso, double valorDiario);

    List<Multa> findByUsuarioAndStatusPagamento(Usuario usuario, StatusPagamento status);

    Double buscarTotalDividaPorUsuario(Long usuarioId);
}