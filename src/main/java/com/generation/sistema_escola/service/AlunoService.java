package com.generation.sistema_escola.service;

import com.generation.sistema_escola.model.Aluno;
import com.generation.sistema_escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(long id) {
        return alunoRepository.findById(id);
    }

    public Aluno save(Aluno aluno) {

        if (alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }
        return alunoRepository.save(aluno);
    }

    public Aluno update(Long id, Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
        aluno.setNome(alunoDetails.getNome());
        aluno.setIdade(alunoDetails.getIdade());
        aluno.setNotaPrimeiroModulo(alunoDetails.getNotaPrimeiroModulo());
        aluno.setNotaSegundoModulo(alunoDetails.getNotaSegundoModulo());

        return alunoRepository.save(aluno);
    }

    public void delete(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
        alunoRepository.delete(aluno);
    }

    public Double calcularMedia(Aluno aluno) {
        return (aluno.getNotaPrimeiroModulo() + aluno.getNotaSegundoModulo()) / 2.0;
    }
}
