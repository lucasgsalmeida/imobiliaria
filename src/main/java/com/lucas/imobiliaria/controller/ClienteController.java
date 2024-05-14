package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.users.UsuarioRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuarioResponseDTO;
import com.lucas.imobiliaria.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/user")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        return service.getUsuarioById(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioResponseDTO data) {
        return service.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.register(data, userDetails);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerAdmin(@RequestBody @Valid UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.registerAdministrator(data, userDetails);
    }

    @PostMapping("/register/master")
    public ResponseEntity registerMaster(@RequestBody UsuarioRequestDTO data) {
        return service.registerMaster(data);
    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getUsuarioAndCliente(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getUsuarioAndCliente(userDetails);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String authorizationHeader) {
        return service.verifyToken(authorizationHeader);
    }

}
