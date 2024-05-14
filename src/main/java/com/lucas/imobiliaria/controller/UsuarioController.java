package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.users.UsuarioRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuarioResponseDTO;
import com.lucas.imobiliaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuariosService;

    @GetMapping("/user")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        return usuariosService.getUsuarioById(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuarioResponseDTO data) {
        return usuariosService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return usuariosService.register(data, userDetails);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerAdmin(@RequestBody @Validated UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return usuariosService.registerAdministrator(data, userDetails);
    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getUsuarioAndCliente(@AuthenticationPrincipal UserDetails userDetails) {
        return usuariosService.getUsuarioAndCliente(userDetails);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String authorizationHeader) {
        return usuariosService.verifyToken(authorizationHeader);
    }
    }
