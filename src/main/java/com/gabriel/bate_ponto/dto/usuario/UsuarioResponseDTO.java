package com.gabriel.bate_ponto.dto.usuario;

import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Role;

public record UsuarioResponseDTO(String email, String nome, boolean ativo, Role role, Cargo cargo) {
}
