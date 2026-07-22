package com.sistema_empresa.demo.service;

import com.sistema_empresa.demo.domain.entity.*;
import com.sistema_empresa.demo.domain.enums.StatusAgendamento;
import com.sistema_empresa.demo.dto.request.CriarAgendamentoRequest;
import com.sistema_empresa.demo.dto.response.AgendamentoResponse;
import com.sistema_empresa.demo.exception.ConflitoHorarioException;
import com.sistema_empresa.demo.repository.AgendamentoRepository;
import com.sistema_empresa.demo.repository.ProfissionalRepository;
import com.sistema_empresa.demo.repository.ServicoRepository;
import com.sistema_empresa.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public AgendamentoResponse criar(CriarAgendamentoRequest request, Long clienteId) {

        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.profissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        Servico servico = servicoRepository.findById(request.servicoId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        LocalDateTime inicio = request.dataHoraInicio();
        LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoMinutos());

        List<Agendamento> conflitos = agendamentoRepository.findConflitos(
                profissional.getId(), inicio, fim);

        if (!conflitos.isEmpty()) {
            throw new ConflitoHorarioException(
                    "O profissional já possui um agendamento nesse horário");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(inicio);
        agendamento.setDataHoraFim(fim);
        agendamento.setStatus(StatusAgendamento.PENDENTE);

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return toResponse(salvo);
    }

    private AgendamentoResponse toResponse(Agendamento a) {
        return new AgendamentoResponse(
                a.getId(),
                a.getCliente().getNome(),
                a.getProfissional().getUsuario().getNome(),
                a.getServico().getNome(),
                a.getDataHoraInicio(),
                a.getDataHoraFim(),
                a.getStatus()
        );
    }
}
