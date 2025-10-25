package com.example.api.domain.services;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.models.Item;

public interface ItemServiceInterface extends BaseServiceInterface<Item, Long> {

    int consultarDisponibilidade(Long itemId);

    default Item atualizarEstoque(Long id, Integer novaQuantidade) {
        return null;
    }
}
