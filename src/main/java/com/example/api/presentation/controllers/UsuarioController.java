package com.example.api.presentation.controllers;

import com.example.api.domain.models.Usuario;
import com.example.api.domain.services.UsuarioServiceInterface;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

    private  final UsuarioServiceInterface usuarioService;

    public UsuarioController(UsuarioServiceInterface usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll(){
        return  usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getById(@PathVariable long id) { return usuarioService.findById(id); }

    @PostMapping()
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
}
