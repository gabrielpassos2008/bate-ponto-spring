package com.gabriel.bate_ponto.repository;

import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto,Long> {

    RegistroPonto findTopByUsuarioAndDataOrderByHoraDesc( Usuario usuario, LocalDate data);

    Optional<List<RegistroPonto>> findByUsuarioAndData(Usuario usuario, LocalDate data);

    boolean existsByDataAndUsuarioAndTipo(LocalDate data, Usuario usuario, String tipo);
}
