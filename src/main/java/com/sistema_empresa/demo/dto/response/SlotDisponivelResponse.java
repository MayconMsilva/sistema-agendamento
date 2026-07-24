package com.sistema_empresa.demo.dto.response;

import java.time.LocalDateTime;

public record SlotDisponivelResponse(
        LocalDateTime inicio,
        LocalDateTime fim
) {}
