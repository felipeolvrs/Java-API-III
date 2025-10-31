package com.example.api.domain.repositories;

import com.example.api.domain.enums.TipoItem;
import com.example.api.domain.models.Item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryInterface {

    List<Item> findByCategoria(TipoItem categoria);
    List<Item> findByEstoqueGreaterThan(int quantidadeMinima);
    Optional<Object> findByNome(@NotNull @Size(min = 3, max = 120) String nome);


}
