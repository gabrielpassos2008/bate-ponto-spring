package com.gabriel.bate_ponto.dto.registroPonto;

import com.gabriel.bate_ponto.model.TipoPonto;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroPontoResponse(LocalDate data, LocalTime hora, TipoPonto tipo) {
}
