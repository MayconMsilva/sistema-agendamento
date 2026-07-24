package com.sistema_empresa.demo.service;

import com.sistema_empresa.demo.domain.entity.Agendamento;
import com.sistema_empresa.demo.domain.entity.Empresa;
import com.sistema_empresa.demo.domain.entity.Profissional;
import com.sistema_empresa.demo.domain.entity.Servico;
import com.sistema_empresa.demo.domain.enums.StatusAgendamento;
import com.sistema_empresa.demo.dto.response.SlotDisponivelResponse;
import com.sistema_empresa.demo.repository.AgendamentoRepository;
import com.sistema_empresa.demo.repository.ProfissionalRepository;
import com.sistema_empresa.demo.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadeService {

    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public List<SlotDisponivelResponse> horariosDisponiveis(
            Long profissionalId, Long servicoId, LocalDate data) {

        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Servico servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        Empresa empresa = profissional.getEmpresa();
        int duracao = servico.getDuracaoMinutos();

        LocalDateTime inicioDia = LocalDateTime.of(data, empresa.getHorarioAbertura());
        LocalDateTime fimDia = LocalDateTime.of(data, empresa.getHorarioFechamento());

        List<Agendamento> agendamentosDoDia = agendamentoRepository
                .findByProfissionalIdAndDataHoraInicioBetweenAndStatusNot(
                        profissionalId, inicioDia, fimDia, StatusAgendamento.CANCELADO);

        List<SlotDisponivelResponse> slots = new ArrayList<>();
        LocalDateTime slotAtual = inicioDia;

        while (!slotAtual.plusMinutes(duracao).isAfter(fimDia)) {
            LocalDateTime slotFim = slotAtual.plusMinutes(duracao);
            final LocalDateTime inicioCheck = slotAtual;

            boolean ocupado = agendamentosDoDia.stream().anyMatch(a ->
                    inicioCheck.isBefore(a.getDataHoraFim()) &&
                            slotFim.isAfter(a.getDataHoraInicio())
            );

            if (!ocupado) {
                slots.add(new SlotDisponivelResponse(slotAtual, slotFim));
            }

            slotAtual = slotAtual.plusMinutes(duracao);
        }

        return slots;
    }
}
