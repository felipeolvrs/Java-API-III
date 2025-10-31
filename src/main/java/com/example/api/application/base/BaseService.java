package com.example.api.application.base;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.exception.RecursoNaoEncontradoException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> implements BaseServiceInterface<T, ID> {

    protected final JpaRepository<T, ID> repository;
    protected final String nomeEntidade;

    protected BaseService(JpaRepository<T, ID> repository, String nomeEntidade) {
        this.repository = repository;
        this.nomeEntidade = nomeEntidade;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public T buscarPorId(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        nomeEntidade + " não encontrado(a) na base de dados"
                ));
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
