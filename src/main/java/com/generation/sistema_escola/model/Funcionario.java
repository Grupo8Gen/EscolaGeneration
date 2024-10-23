package com.generation.sistema_escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String nome;

    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull
    @Size(min = 6)
    private String senha;

    @NotBlank
    private String cargo;


    public Funcionario(Long id, String nome, String email, String senha, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }



    public @NotBlank String getCargo() {
        return cargo;
    }

    public void setCargo(@NotBlank String cargo) {
        this.cargo = cargo;
    }

    public @NotNull @Size(min = 6) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(min = 6) String senha) {
        this.senha = senha;
    }

    public @NotBlank @Email(message = "E-mail inválido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email(message = "E-mail inválido") String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 3) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3) String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
