package com.example.api.presentation.controllers;

import com.example.api.domain.models.Emprestimo;
import com.example.api.domain.services.EmprestimoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoController {

    private final EmprestimoServiceInterface emprestimoService;

    public EmprestimoController(EmprestimoServiceInterface emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAll(){
        return ResponseEntity.ok(emprestimoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getById(@PathVariable long id) {
        return emprestimoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> create(@RequestBody @Valid Emprestimo emprestimo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestimoService.criarEmprestimo(emprestimo));
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<Emprestimo> renovar(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.renovarEmprestimo(id));
    }

    @PostMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolverEmprestimo(id));
    }
}