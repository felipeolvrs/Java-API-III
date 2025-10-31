package com.example.api.domain.models;

import com.example.api.domain.enums.StatusEmprestimo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @NotNull
    @Column(name = "data_prevista_devolucao")
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "data_devolucao_real")
    private LocalDate dataDevolucaoReal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @NotNull
    @Column(name = "num_renovacoes")
    private Integer numRenovacoes = 0;
}