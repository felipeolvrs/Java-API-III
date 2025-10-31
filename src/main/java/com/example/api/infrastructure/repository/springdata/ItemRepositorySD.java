package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.models.Item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepositorySD extends JpaRepository<Item, Long> {

    Optional<Object> findByNome(@NotNull @Size(min = 3, max = 120) String nome);

    Long id(Long id);
}