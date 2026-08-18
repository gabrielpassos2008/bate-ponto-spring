package com.gabriel.bate_ponto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BancoHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataReferencia;
    private LocalTime MinutosTrabalhados;
    private LocalTime MinutosEsperados;
    private LocalTime SaldoEsperado;

    @ManyToOne
    @JoinColumn(name = "fk_cargo_id")
    private Cargo cargo;
}
