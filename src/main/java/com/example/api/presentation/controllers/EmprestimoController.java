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
        return  emprestimoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Emprestimo> getById(@PathVariable long id) {
        return  emprestimoService.findById(id);
    }

    @PostMapping()
    public Emprestimo create(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.criarEmprestimo(emprestimo);
    }

    @PostMapping("/{id}/renovar")
    public Emprestimo renovar(@PathVariable Long id) {
        return emprestimoService.renovarEmprestimo(id);
    }

    @PostMapping("/{id}/devolver")
    public Emprestimo devolver(@PathVariable Long id) {

        return emprestimoService.devolverEmprestimo(id);
    }
}