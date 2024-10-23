package com.generation.sistema_escola.controller;

import com.generation.sistema_escola.model.Aluno;
import com.generation.sistema_escola.model.Turma;
import com.generation.sistema_escola.repository.AlunoRepository;
import com.generation.sistema_escola.service.AlunoService;
import com.generation.sistema_escola.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;
    
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createAluno(@RequestBody Aluno aluno) {
        try {
            
            Aluno novoAluno = alunoService.save(aluno);
            
            double mediaNotas = (novoAluno.getNotaPrimeiroModulo() + novoAluno.getNotaSegundoModulo()) / 2;
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", novoAluno.getId());
            response.put("nome", novoAluno.getNome());
            response.put("email", novoAluno.getEmail());
            response.put("idade", novoAluno.getIdade());
            response.put("notaPrimeiroModulo", novoAluno.getNotaPrimeiroModulo());
            response.put("notaSegundoModulo", novoAluno.getNotaSegundoModulo());
            response.put("media", mediaNotas);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        try {
            Aluno alunoAtualizado = alunoService.update(id, alunoDetails);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
