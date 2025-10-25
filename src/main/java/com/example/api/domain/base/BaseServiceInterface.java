package com.example.api.domain.base;

import com.example.api.domain.models.Item;

import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T, ID> {

    List<T> getAll();
    Optional<T> getById(ID id);
    T save(T entity);
    void deleteById(ID id);
}