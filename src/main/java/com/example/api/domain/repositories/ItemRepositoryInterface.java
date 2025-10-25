package com.example.api.domain.repositories;

import com.example.api.domain.enums.TipoItem;
import com.example.api.domain.models.Item;

import java.util.List;

public interface ItemRepositoryInterface {

    List<Item> findByCategoria(TipoItem categoria);

    List<Item> findByEstoqueGreaterThan(int quantidadeMinima);

}
