package com.example.api.presentation.controllers;

import com.example.api.application.services.MultaService;
import com.example.api.domain.models.Multa;
import com.example.api.domain.services.MultaServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/multas")
public class MultaController {

    private final MultaServiceInterface multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public ResponseEntity<List<Multa>> getAll(){
        return ResponseEntity.ok(multaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Multa> getById(@PathVariable long id) {
        return multaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Multa> create(@RequestBody Multa multa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.save(multa));
    }
}