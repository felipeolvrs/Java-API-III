package com.example.api.presentation.controllers;

import com.example.api.application.services.MultaService;
import com.example.api.domain.models.Multa;
import com.example.api.domain.services.MultaServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/multa")
public class MultaController{
    private final MultaServiceInterface multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public List<Multa> getAll(){
        return  multaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Multa> getById(@PathVariable long id) { return  multaService.findById(id); }

    @PostMapping()
    public Multa create(@RequestBody Multa multa) {
        return multaService.save(multa);
    }
}
