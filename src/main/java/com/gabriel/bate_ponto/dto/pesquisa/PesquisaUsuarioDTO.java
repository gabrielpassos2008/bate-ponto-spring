package com.gabriel.bate_ponto.dto.pesquisa;

import com.gabriel.bate_ponto.model.Cargo;
import com.gabriel.bate_ponto.model.Role;

public record PesquisaUsuarioDTO(String pesquisa, Long idCargo) {
}
