package com.example.api.domain.services;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.models.Multa;
import java.util.List;

public interface MultaServiceInterface extends BaseServiceInterface<Multa, Long> {

    List<Multa> consultarMultasPorUsuario(Long usuarioId);

    Multa registrarMulta(Long emprestimoId, long diasAtraso, double valorDiario);

}
