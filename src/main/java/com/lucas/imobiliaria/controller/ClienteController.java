package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/id/{id}")
    public ResponseEntity getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllCliente() {
        return clienteService.getAllCliente();
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveCliente(@Valid @RequestBody ClienteRequestDTO data) {
        return clienteService.register(data);
    }
}
