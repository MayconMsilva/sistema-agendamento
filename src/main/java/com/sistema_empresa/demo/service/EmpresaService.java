package com.sistema_empresa.demo.service;

import com.sistema_empresa.demo.domain.entity.Empresa;
import com.sistema_empresa.demo.dto.request.CriarEmpresaRequest;
import com.sistema_empresa.demo.dto.response.EmpresaResponse;
import com.sistema_empresa.demo.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Transactional
    public EmpresaResponse criar(CriarEmpresaRequest request) {
        if (empresaRepository.existsBySlug(request.slug())) {
            throw new IllegalArgumentException("Slug já em uso");
        }

        Empresa empresa = new Empresa();
        empresa.setNome(request.nome());
        empresa.setSlug(request.slug());
        empresa.setHorarioAbertura(request.horarioAbertura());
        empresa.setHorarioFechamento(request.horarioFechamento());

        return toResponse(empresaRepository.save(empresa));
    }

    public EmpresaResponse buscarPorSlug(String slug) {
        Empresa empresa = empresaRepository.findBySlug(slug)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        return toResponse(empresa);
    }

    private EmpresaResponse toResponse(Empresa e) {
        return new EmpresaResponse(
                e.getId(), e.getNome(), e.getSlug(),
                e.getHorarioAbertura(), e.getHorarioFechamento());
    }
}
