package com.example.api.presentation.controllers;

import com.example.api.domain.services.ItemServiceInterface;
import com.example.api.domain.models.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemServiceInterface itemService;

    public ItemController(ItemServiceInterface itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll(){
        return  itemService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Item> getById(@PathVariable long id) { return  itemService.getById(id); }

    @PostMapping()
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }



}
