package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.enums.StatusReserva;
import com.example.api.domain.models.Item;
import com.example.api.domain.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservaRepositorySD extends JpaRepository<Reserva, Long> {

    Optional<Reserva> findByItemAndStatus(Item item, StatusReserva status);

    @Query("SELECT COUNT(r) FROM Reserva r " +
            "WHERE r.item.id = :itemId " +
            "AND r.status = 'ATIVA'")
    Long countReservasAtivasPorItem(@Param("itemId") Long itemId);

    List<Reserva> findByUsuario_Id(Long usuarioId);
}