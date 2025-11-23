package com.example.api.domain.repositories;

import com.example.api.domain.base.BaseServiceInterface;
import com.example.api.domain.enums.StatusReserva;
import com.example.api.domain.models.Item;
import com.example.api.domain.models.Reserva;

import java.util.Optional;

public interface ReservaRepositoryInterface  extends BaseServiceInterface<Reserva, Long> {

    Optional<Reserva> findByItemAndStatus(Item item, StatusReserva status);
}
