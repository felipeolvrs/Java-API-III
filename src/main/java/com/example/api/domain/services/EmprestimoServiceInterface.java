package com.example.api.domain.services;
import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.models.Emprestimo;

public interface EmprestimoServiceInterface extends BaseServiceInterface<Emprestimo, Long> {

    Emprestimo criarEmprestimo(Emprestimo emprestimo);
    Emprestimo renovarEmprestimo(Long id);
    Emprestimo devolverEmprestimo(Long id);
}
