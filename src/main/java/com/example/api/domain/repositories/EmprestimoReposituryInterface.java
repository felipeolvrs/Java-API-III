package com.example.api.domain.repositories;

import com.example.api.domain.enums.StatusRenovacao;
import com.example.api.domain.models.Emprestimo;

import java.util.List;

public interface EmprestimoReposituryInterface {

    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByItemIdAndDevolvidoFalse(Long itemId);
    int countByIdAndStatusRenovacao(Long emprestimoId, StatusRenovacao status);



}
