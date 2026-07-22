package com.sistema_empresa.demo.dto.response;

public record AuthResponse(
        String token,
        Long usuarioId,
        String nome,
        String role
) {}
