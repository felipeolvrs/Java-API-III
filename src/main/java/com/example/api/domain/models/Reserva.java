package com.example.api.domain.models;

import com.example.api.domain.enums.StatusReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusReserva status;

    @NotNull
    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;

    @NotNull
    @Column(name = "data_expiracao")
    private LocalDateTime dataExpiracao;
}