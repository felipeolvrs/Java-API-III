package com.example.api.presentation.controllers;

import com.example.api.domain.models.Item;
import com.example.api.application.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll(){
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable long id) {
        // Ajuste para lidar com possibilidade de null ou exception se preferir
        return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.criarItem(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        //TODO: Fazer as validações do delete
        itemService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/disponibilidade")
    public ResponseEntity<Integer> getDisponibilidade(@PathVariable Long id) {
        //TODO: Voltando a quantidade de estoque
        return ResponseEntity.ok(itemService.consultarDisponibilidade(id));
    }

    @PutMapping("/{id}/estoque")
    //TODO: Verificar
    public ResponseEntity<Item> updateEstoque(@PathVariable Long id, @RequestBody Integer novaQuantidade) {
        return ResponseEntity.ok(itemService.atualizar(id, novaQuantidade));
    }
}