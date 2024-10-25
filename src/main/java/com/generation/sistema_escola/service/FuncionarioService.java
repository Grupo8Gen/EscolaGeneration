package com.generation.sistema_escola.service;

import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Optional<Funcionario> getById(long id) {
        return repository.findById(id);
    }

    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    public Funcionario saveFuncionario(Funcionario funcionario) {
        if (repository.existsByEmail(funcionario.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }
        return repository.save(funcionario);
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
        Optional<Funcionario> funcionarioUpdate = repository.findById(id);
        if (funcionarioUpdate.isEmpty()) {
            throw new RuntimeException("Funcionário não encontrado");
        }

        Funcionario funcionarioExistente = funcionarioUpdate.get();
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setEmail(funcionario.getEmail());
        funcionarioExistente.setSenha(funcionario.getSenha());
        funcionarioExistente.setCargo(funcionario.getCargo());

        try {
            return repository.save(funcionarioExistente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o funcionário", e);
        }
    }

    public Funcionario deleteFuncionarioById(Long id) {
        return repository.findById(id).map(funcionario -> {
            repository.deleteById(id);
            return funcionario;
        }).orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado para o ID: " + id));
    }
}
