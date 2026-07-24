package com.sistema_empresa.demo.controller;

import com.sistema_empresa.demo.dto.request.CriarServicoRequest;
import com.sistema_empresa.demo.dto.response.ServicoResponse;
import com.sistema_empresa.demo.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponse> criar(@Valid @RequestBody CriarServicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listarPorEmpresa(@RequestParam Long empresaId) {
        return ResponseEntity.ok(servicoService.listarPorEmpresa(empresaId));
    }
}