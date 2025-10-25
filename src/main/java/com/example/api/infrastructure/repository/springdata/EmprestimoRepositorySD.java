package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepositorySD extends JpaRepository<Emprestimo, Long> {
}