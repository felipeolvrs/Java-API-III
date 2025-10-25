package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.models.Item;
import com.example.api.domain.services.ItemServiceInterface;
import com.example.api.infrastructure.repository.springdata.ItemRepositorySD;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<Item, Long> implements ItemServiceInterface {

    private final ItemRepositorySD itemRepository;

    public ItemService(ItemRepositorySD itemRepository) {
        super(itemRepository);
        this.itemRepository = itemRepository;
    }

    @Override
    public Item atualizarEstoque(Long id, Integer novaQuantidade) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));
        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    @Override
    public int consultarDisponibilidade(Long id) {
        return 0;
    }

    //TODO: Implementar os serviços da interface

}
