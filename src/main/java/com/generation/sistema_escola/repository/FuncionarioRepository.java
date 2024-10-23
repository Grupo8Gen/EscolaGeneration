package com.generation.sistema_escola.repository;

import com.generation.sistema_escola.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByEmail(String email);
}

