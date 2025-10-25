package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositorySD extends JpaRepository<Item, Long> {
}