package com.gabriel.bate_ponto.dto.registroPonto;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroPontoResponse(LocalDate data, LocalTime hora, String tipo) {
}
