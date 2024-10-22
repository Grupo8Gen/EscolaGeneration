package com.generation.sistema_escola.controller;

import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> GetById(@PathVariable long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        List<Funcionario> funcionarios = service.getAll();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping
    public ResponseEntity<Funcionario> post(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = service.saveFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    @PutMapping
    public ResponseEntity<Funcionario> put(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioAtualizado = service.updateFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteFuncionarioById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
