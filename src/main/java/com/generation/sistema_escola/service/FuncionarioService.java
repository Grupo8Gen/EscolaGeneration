package com.generation.sistema_escola.service;

import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (repository.existsByEmail(funcionario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        try {
            return repository.save(funcionario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o funcionário", e);
        }
    }


    public Funcionario updateFuncionario(Funcionario funcionario) {
        try {
            return repository.save(funcionario);
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
