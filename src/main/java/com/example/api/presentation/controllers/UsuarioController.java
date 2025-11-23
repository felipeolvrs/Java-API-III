package com.example.api.presentation.controllers;

import com.example.api.domain.models.Usuario;
import com.example.api.domain.services.UsuarioServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioServiceInterface usuarioService;

    public UsuarioController(UsuarioServiceInterface usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping("/{id}/dividas")
    public ResponseEntity<BigDecimal> consultarDividas(@PathVariable Long id) {
        BigDecimal total = usuarioService.calcularTotalDivida(id);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/{id}/dividas/quitar")
    public ResponseEntity<Void> quitarDividas(@PathVariable Long id) {
        usuarioService.quitarDividas(id);
        return ResponseEntity.noContent().build();
    }
}