package com.example.api.domain.repositories;

import com.example.api.domain.models.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryInterface {

    Optional<Usuario> findByNome(String nome);

    boolean existsByDividasGreaterThan(double valorMinimo);
}
