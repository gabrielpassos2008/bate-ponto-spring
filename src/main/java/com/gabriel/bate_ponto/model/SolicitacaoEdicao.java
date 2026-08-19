package com.gabriel.bate_ponto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoEdicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAntiga;
    private LocalTime horaAntiga;
    private LocalDate dataNova;
    private LocalTime horaNova;
    private LocalDateTime dataAnalise;
    private String justificativa;
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_registro_ponto_id")
    private RegistroPonto registroPonto;

    @ManyToOne
    @JoinColumn(name = "fk_aprovacao_id")
    private Usuario aprovacao;



}
