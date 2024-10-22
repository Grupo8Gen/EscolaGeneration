package com.generation.sistema_escola.service;

import com.generation.sistema_escola.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.generation.sistema_escola.model.Turma;
import com.generation.sistema_escola.repository.TurmaRepository;


import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public ResponseEntity<List<Turma>> listarTodas() {
        List<Turma> turmas = turmaRepository.findAll();
        return ResponseEntity.ok(turmas);
    }

    public ResponseEntity<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .map(turma -> ResponseEntity.ok(turma))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Turma> salvar(Turma turma) {
        for (Aluno aluno : turma.getAlunos()) {
            if (turmaRepository.existsByAlunos_IdAndHorario(aluno.getId(), turma.getHorario())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

        Turma turmaSalva = turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva);
    }

    public ResponseEntity<Turma> atualizar(Long id, Turma turmaAtualizada) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Turma turma = turmaRepository.findById(id).get();

        for (Aluno aluno : turmaAtualizada.getAlunos()) {
            boolean conflito = turmaRepository.existsByAlunos_IdAndHorario(aluno.getId(), turmaAtualizada.getHorario());
            if (conflito) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        turma.setNome(turmaAtualizada.getNome());
        turma.setInstrutor(turmaAtualizada.getInstrutor());
        turma.setAlunos(turmaAtualizada.getAlunos());
        turma.setHorario(turmaAtualizada.getHorario());

        Turma turmaSalva = turmaRepository.save(turma);
        return ResponseEntity.ok(turmaSalva);
    }

    public ResponseEntity<Void> deletar(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
