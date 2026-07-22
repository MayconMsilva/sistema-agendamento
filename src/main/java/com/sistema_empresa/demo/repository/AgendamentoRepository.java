package com.sistema_empresa.demo.repository;

import com.sistema_empresa.demo.domain.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByClienteId(Long clienteId);

    List<Agendamento> findByProfissionalIdAndDataHoraInicioBetween(
            Long profissionalId, LocalDateTime inicio, LocalDateTime fim);

    // Query que verifica se existe algum agendamento do profissional
    // que se sobrepõe ao intervalo informado (usada na regra de conflito)
    @Query("""
        SELECT a FROM Agendamento a
        WHERE a.profissional.id = :profissionalId
        AND a.status <> 'CANCELADO'
        AND a.dataHoraInicio < :fim
        AND a.dataHoraFim > :inicio
    """)
    List<Agendamento> findConflitos(
            @Param("profissionalId") Long profissionalId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}
