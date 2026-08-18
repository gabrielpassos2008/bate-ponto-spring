package com.gabriel.bate_ponto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;
    private String nome;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "fk_gestor_id")
    private Usuario gestor;

    @OneToMany(mappedBy = "gestor")
    private List<Usuario> subordinados;


}
