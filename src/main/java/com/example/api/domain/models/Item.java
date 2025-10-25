package com.example.api.domain.models;

import com.example.api.domain.enums.TipoItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 120)
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItem categoria;

    @NotNull
    @Min(0)
    private Integer estoque;

    @NotNull
    @Min(0)
    private Integer quantidade;

}
