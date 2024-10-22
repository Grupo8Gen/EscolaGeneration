package com.generation.sistema_escola.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.generation.sistema_escola.model.Turma;
import com.generation.sistema_escola.service.TurmaService;
import java.util.List;

@RestController
@RequestMapping("/turmas")
@CrossOrigin("*")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    
    @GetMapping("/todas")
    public ResponseEntity<List<Turma>> listarTodas() {
        return turmaService.listarTodas();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorId(id);
    }

    
    @PostMapping("/cadastrar")
    public ResponseEntity<Turma> salvar(@RequestBody Turma turma) {
        return turmaService.salvar(turma);
    }

    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        return turmaService.atualizar(id, turmaAtualizada);
    }

    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return turmaService.deletar(id);
    }
}

