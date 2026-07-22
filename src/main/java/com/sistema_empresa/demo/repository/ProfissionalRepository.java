package com.sistema_empresa.demo.repository;

import com.sistema_empresa.demo.domain.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    List<Profissional> findByEmpresaId(Long empresaId);
    Optional<Profissional> findByUsuarioId(Long usuarioId);
}