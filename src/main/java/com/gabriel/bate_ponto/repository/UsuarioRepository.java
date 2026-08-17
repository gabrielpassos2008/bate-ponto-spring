package com.gabriel.bate_ponto.repository;

import com.gabriel.bate_ponto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
}
