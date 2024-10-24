package com.generation.sistema_escola.model;

public record RegisterDTO(String email, String senha, String nome, String cargo, UserRole role) {
}
