package com.generation.sistema_escola.controller;

import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.model.FuncionarioDTO;
import com.generation.sistema_escola.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> GetById(@PathVariable long id) {
        return service.getById(id)
                .map(funcionario -> new FuncionarioDTO(
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getEmail(),
                        funcionario.getCargo()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FuncionarioDTO>> getAll() {
        List<Funcionario> funcionarios = service.getAll();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FuncionarioDTO> funcionariosDTO = funcionarios.stream()
                .map(funcionario -> new FuncionarioDTO(
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getEmail(),
                        funcionario.getCargo()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(funcionariosDTO);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Funcionario funcionario) {
        try {
            Funcionario novoFuncionario = service.saveFuncionario(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o funcionário: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> put(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario funcionarioAtualizado = service.updateFuncionario(id, funcionario);
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteFuncionarioById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
