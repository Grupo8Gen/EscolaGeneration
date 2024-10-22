package com.generation.sistema_escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter no mínimo 3 e no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "A idade não pode ser nula")
    @Min(value = 0, message = "A idade deve ser um valor positivo")
    private Integer idade;

    @NotNull(message = "A nota do primeiro módulo não pode ser nula")
    @Min(value = 0, message = "A nota do primeiro módulo deve ser positiva")
    @Column(name = "nota_primeiro_modulo")
    private Double notaPrimeiroModulo;

    @NotNull(message = "A nota do segundo módulo não pode ser nula")
    @Min(value = 0, message = "A nota do segundo módulo deve ser positiva")
    @Column(name = "nota_segundo_modulo")
    private Double notaSegundoModulo;

    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Column(unique = true) 
    private String email;

    @Transient 
    private Double media;
  
    public Aluno() {
    }

    public Aluno(Long id, String nome, Integer idade, Double notaPrimeiroModulo, Double notaSegundoModulo, String email) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.notaPrimeiroModulo = notaPrimeiroModulo;
        this.notaSegundoModulo = notaSegundoModulo;
        this.email = email;
        this.media = calcularMedia(); 
    }

    
    public Double calcularMedia() {
        return (notaPrimeiroModulo + notaSegundoModulo) / 2; // Método para calcular a média
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getNotaPrimeiroModulo() {
        return notaPrimeiroModulo;
    }

    public void setNotaPrimeiroModulo(Double notaPrimeiroModulo) {
        this.notaPrimeiroModulo = notaPrimeiroModulo;
        this.media = calcularMedia(); // Atualiza a média sempre que a nota é alterada
    }

    public Double getNotaSegundoModulo() {
        return notaSegundoModulo;
    }

    public void setNotaSegundoModulo(Double notaSegundoModulo) {
        this.notaSegundoModulo = notaSegundoModulo;
        this.media = calcularMedia(); // Atualiza a média sempre que a nota é alterada
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMedia() {
        return calcularMedia(); // Retorna a média atualizada
    }

}
