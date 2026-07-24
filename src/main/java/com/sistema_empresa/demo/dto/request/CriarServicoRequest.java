package com.sistema_empresa.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CriarServicoRequest(
        @NotNull Long empresaId,
        @NotBlank String nome,
        @NotNull @Positive Integer duracaoMinutos,
        @NotNull @Positive BigDecimal preco
) {}
