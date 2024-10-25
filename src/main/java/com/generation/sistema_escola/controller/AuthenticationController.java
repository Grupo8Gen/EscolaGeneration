package com.generation.sistema_escola.controller;

import com.generation.sistema_escola.infra.security.TokenService;
import com.generation.sistema_escola.model.AuthenticationDTO;
import com.generation.sistema_escola.model.Funcionario;
import com.generation.sistema_escola.model.LoginResponseDTO;
import com.generation.sistema_escola.model.RegisterDTO;
import com.generation.sistema_escola.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private FuncionarioRepository repository;
    @Autowired(required = false)
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Funcionario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Funcionario newFuncionario = new Funcionario(data.nome(), data.cargo(), data.email(), encryptedPassword, data.role());
        this.repository.save(newFuncionario);
        return ResponseEntity.ok("Funcionario cadastrado: " + newFuncionario.getNome() + ", com codigo: " + newFuncionario.getId());
    }
}
