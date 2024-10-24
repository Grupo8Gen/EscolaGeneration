package com.generation.sistema_escola.repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.sistema_escola.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    boolean existsByNome(String nome);

    boolean existsByAlunos_IdAndHorario(Long alunoId, LocalTime localTime);
}

