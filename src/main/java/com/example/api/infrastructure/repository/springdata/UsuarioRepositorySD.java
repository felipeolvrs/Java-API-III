package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorySD extends JpaRepository<Usuario, Long> {
}
