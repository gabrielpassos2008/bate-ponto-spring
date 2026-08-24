package com.gabriel.bate_ponto.dto.usuario;

import com.gabriel.bate_ponto.model.Usuario;

public record UsuarioCreateDTO(String email, String senha, String nome, boolean ativo, String Role) {
}
