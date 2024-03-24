package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.infra.security.TokenService;
import com.lucas.imobiliaria.model.domain.repository.ClienteRepository;
import com.lucas.imobiliaria.model.domain.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.LoginResponseDTO;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.domain.users.UsuariosRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuariosResponseDTO;
import com.lucas.imobiliaria.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/user")
    public ResponseEntity<UsuariosResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        return usuariosService.getUsuarioById(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuariosResponseDTO data) {
        return usuariosService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UsuariosRequestDTO data) {
        return usuariosService.register(data);
    }
}
