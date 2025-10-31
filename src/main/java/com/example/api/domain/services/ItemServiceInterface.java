package com.example.api.domain.services;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.models.Item;

import java.util.Optional;

public interface ItemServiceInterface extends BaseServiceInterface<Item, Long> {

    Item criarItem(Item item);
    int consultarDisponibilidade(Long itemId);
    Item atualizar(Long id, Integer novaQuantidade);
    Item reduzirEstoque(Long id, Integer quantidade);
    Item aumentarEstoque(Long id, Integer quantidade);

}