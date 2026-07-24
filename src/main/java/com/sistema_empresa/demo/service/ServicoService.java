package com.sistema_empresa.demo.service;

import com.sistema_empresa.demo.domain.entity.Empresa;
import com.sistema_empresa.demo.domain.entity.Servico;
import com.sistema_empresa.demo.dto.request.CriarServicoRequest;
import com.sistema_empresa.demo.dto.response.ServicoResponse;
import com.sistema_empresa.demo.repository.EmpresaRepository;
import com.sistema_empresa.demo.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final EmpresaRepository empresaRepository;

    @Transactional
    public ServicoResponse criar(CriarServicoRequest request) {
        Empresa empresa = empresaRepository.findById(request.empresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));

        Servico servico = new Servico();
        servico.setEmpresa(empresa);
        servico.setNome(request.nome());
        servico.setDuracaoMinutos(request.duracaoMinutos());
        servico.setPreco(request.preco());

        return toResponse(servicoRepository.save(servico));
    }

    public List<ServicoResponse> listarPorEmpresa(Long empresaId) {
        return servicoRepository.findByEmpresaId(empresaId)
                .stream().map(this::toResponse).toList();
    }

    private ServicoResponse toResponse(Servico s) {
        return new ServicoResponse(s.getId(), s.getNome(), s.getDuracaoMinutos(), s.getPreco());
    }
}