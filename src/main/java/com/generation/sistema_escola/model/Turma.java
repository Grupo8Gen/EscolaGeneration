package com.generation.sistema_escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String nome;

    @NotBlank
    private String instrutor;

    @NotNull
    private LocalTime horario;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("turma")
    private List<Aluno> alunos = new ArrayList<>();

    public Turma() {
    }

    public Turma(Long id, @NotBlank @Size(min = 3) String nome, @NotBlank String instrutor, @NotBlank LocalTime horario,
                 List<Aluno> alunos) {
        super();
        this.id = id;
        this.nome = nome;
        this.instrutor = instrutor;
        this.horario = horario;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}

