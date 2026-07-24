package com.sistema_empresa.demo.dto.response;

import java.time.LocalTime;

public record EmpresaResponse(
        Long id,
        String nome,
        String slug,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento
) {}
