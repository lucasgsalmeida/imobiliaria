package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/register")
    public ResponseEntity saveCliente(@RequestBody ClienteRequestDTO data) {
        return clienteService.saveCliente(data);
    }
}
