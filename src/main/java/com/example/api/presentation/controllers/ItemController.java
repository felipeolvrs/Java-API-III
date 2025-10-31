package com.example.api.presentation.controllers;

import com.example.api.domain.models.Item;
import com.example.api.application.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable long id) {
        return itemService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return itemService.criarItem(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.deletarItem(id);
    }

    @GetMapping("/{id}/disponibilidade")
    public int getDisponibilidade(@PathVariable Long id) {
        return itemService.consultarDisponibilidade(id);
    }

    @PutMapping("/{id}/estoque")
    public Item updateEstoque(@PathVariable Long id, @RequestBody Integer novaQuantidade) {
        return itemService.atualizar(id, novaQuantidade);
    }
}