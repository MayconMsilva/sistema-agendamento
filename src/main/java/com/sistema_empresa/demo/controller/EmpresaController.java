package com.sistema_empresa.demo.controller;

import com.sistema_empresa.demo.dto.request.CriarEmpresaRequest;
import com.sistema_empresa.demo.dto.response.EmpresaResponse;
import com.sistema_empresa.demo.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponse> criar(@Valid @RequestBody CriarEmpresaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criar(request));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<EmpresaResponse> buscarPorSlug(@PathVariable String slug) {
        return ResponseEntity.ok(empresaService.buscarPorSlug(slug));
    }
}
