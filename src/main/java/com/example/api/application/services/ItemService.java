package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.models.Item;
import com.example.api.domain.services.ItemServiceInterface;
import com.example.api.exception.RecursoExistenteException;
import com.example.api.exception.RecursoNaoEncontradoException;
import com.example.api.infrastructure.repository.springdata.ItemRepositorySD;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<Item, Long> implements ItemServiceInterface {

    private final ItemRepositorySD itemRepository;

    public ItemService(ItemRepositorySD itemRepository) {
        super(itemRepository, "Item");
        this.itemRepository = itemRepository;
    }

    @Override
    public Item criarItem(Item item) {

        if (itemRepository.findByNome(item.getNome()).isPresent())
            throw new RecursoExistenteException("Item já existe: " + item.getNome());

        if (item.getEstoque() == null)
            item.setEstoque(0);

        return itemRepository.save(item);
    }

    public void deletarItem(Long id) {
        var item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com ID: " + id));

        if (item.getQuantidade() > 0)
            throw new RecursoExistenteException("Não é possível deletar item com estoque disponível.");

        itemRepository.delete(item);
    }

    @Override
    public int consultarDisponibilidade(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado."));

        var quantidade = item.getEstoque();
        return Math.max(0, quantidade);
    }

    @Override
    public Item atualizar(Long id, Integer novaQuantidade) {
        if (novaQuantidade < 0)
            throw new RuntimeException("Estoque não pode ser negativo.");

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado."));

        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    @Override
    public Item reduzirEstoque(Long id, Integer quantidade) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado"));

        int estoqueAtual = item.getEstoque();

        if (estoqueAtual < quantidade)
            throw new RuntimeException("Estoque insuficiente para este empréstimo.");

        var novaQuantidade = estoqueAtual - quantidade;
        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    @Override
    public Item aumentarEstoque(Long id, Integer quantidade) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado"));

        var novaQuantidade = item.getEstoque() + quantidade;

        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }
}