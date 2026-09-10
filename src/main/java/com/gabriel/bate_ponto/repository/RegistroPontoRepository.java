package com.gabriel.bate_ponto.repository;

import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto,Long> {

    RegistroPonto findTopByUsuarioAndDataOrderByHoraDesc( Usuario usuario, LocalDate data);

    boolean existsByDataAndUsuario(LocalDate data, Usuario usuario);
}
