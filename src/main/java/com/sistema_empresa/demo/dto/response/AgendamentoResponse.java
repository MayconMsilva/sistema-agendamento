package com.sistema_empresa.demo.dto.response;

import com.sistema_empresa.demo.domain.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        String clienteNome,
        String profissionalNome,
        String servicoNome,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        StatusAgendamento status
) {}
