package com.sistema_empresa.demo.controller;

import com.sistema_empresa.demo.dto.response.SlotDisponivelResponse;
import com.sistema_empresa.demo.service.DisponibilidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;

    @GetMapping("/{profissionalId}/horarios-disponiveis")
    public List<SlotDisponivelResponse> horariosDisponiveis(
            @PathVariable Long profissionalId,
            @RequestParam Long servicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return disponibilidadeService.horariosDisponiveis(profissionalId, servicoId, data);
    }
}
