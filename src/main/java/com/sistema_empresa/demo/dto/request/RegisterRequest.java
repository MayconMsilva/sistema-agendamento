package com.sistema_empresa.demo.dto.request;

import com.sistema_empresa.demo.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull Role role,
        Long empresaId // opcional, só para ADMIN/PROFISSIONAL
) {}