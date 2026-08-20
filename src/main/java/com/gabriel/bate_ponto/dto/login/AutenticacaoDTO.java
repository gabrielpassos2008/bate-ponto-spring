package com.gabriel.bate_ponto.dto.login;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "Senha é obrigatório")
        String senha) {
}
