package com.sistema_empresa.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CriarEmpresaRequest(
        @NotBlank String nome,
        @NotBlank String slug,
        @NotNull LocalTime horarioAbertura,
        @NotNull LocalTime horarioFechamento
) {}
