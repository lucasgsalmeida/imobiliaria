package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.domain.users.UsuariosRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuariosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UsuariosController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuariosResponseDTO data) {
        var userpw = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UsuariosRequestDTO data) {
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }
}
