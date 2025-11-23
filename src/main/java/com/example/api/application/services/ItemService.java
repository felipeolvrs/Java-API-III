package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.models.Item;
import com.example.api.domain.services.ItemServiceInterface;
import com.example.api.exception.EstoqueInsuficienteException;
import com.example.api.exception.RecursoExistenteException;
import com.example.api.exception.RecursoNaoEncontradoException;
import com.example.api.exception.RegraNegocioException;
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

        if (item.getEstoque() < 0)
            throw new RegraNegocioException("O estoque inicial não pode ser negativo.");

        return itemRepository.save(item);
    }

    public void deletarItem(Long id) {
        var item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com ID: " + id));

        if (item.getEstoque() > 0)
            throw new RegraNegocioException("Não é possível deletar um item que ainda possui estoque físico.");

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
            throw new RegraNegocioException("Estoque não pode ser negativo.");

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
            throw new EstoqueInsuficienteException("Estoque insuficiente para realizar esta operação.");

        var novaQuantidade = estoqueAtual - quantidade;
        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    @Override
    public Item aumentarEstoque(Long id, Integer quantidade) {
        if (quantidade < 0)
            throw new RegraNegocioException("A quantidade para adicionar deve ser positiva.");

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado"));

        var novaQuantidade = item.getEstoque() + quantidade;

        item.setEstoque(novaQuantidade);
        return itemRepository.save(item);
    }
}