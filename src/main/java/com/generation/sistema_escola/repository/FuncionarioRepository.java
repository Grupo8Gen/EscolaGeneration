package com.generation.sistema_escola.repository;

import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByEmail(String email);
    Funcionario findByEmail(String email);
}
