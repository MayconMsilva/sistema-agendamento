package com.sistema_empresa.demo.dto.response;

import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        Integer duracaoMinutos,
        BigDecimal preco
) {}
