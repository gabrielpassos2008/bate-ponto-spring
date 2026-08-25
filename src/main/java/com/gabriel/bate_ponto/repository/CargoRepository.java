package com.gabriel.bate_ponto.repository;

import com.gabriel.bate_ponto.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {
}
