package com.example.api.presentation.controllers;

import com.example.api.domain.models.Emprestimo;
import com.example.api.domain.services.EmprestimoServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoServiceInterface  emprestimoService;

    public EmprestimoController(EmprestimoServiceInterface emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public List<Emprestimo> getAll(){
        return  emprestimoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Emprestimo> getById(@PathVariable long id) { return  emprestimoService.getById(id); }

    @PostMapping()
    public Emprestimo create(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.save(emprestimo);
    }
}