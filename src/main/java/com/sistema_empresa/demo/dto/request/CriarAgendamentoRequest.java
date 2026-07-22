package com.sistema_empresa.demo.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CriarAgendamentoRequest(
        @NotNull Long profissionalId,
        @NotNull Long servicoId,
        @NotNull @Future LocalDateTime dataHoraInicio
) {}
