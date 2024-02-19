package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.model.domain.cliente.ClienteResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository cr;

    @GetMapping("/id/{id}")
    public Clientes getClienteById(@PathVariable Long id) {
        return cr.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + id));
    }

    @GetMapping("/all")
    public List<ClienteResponseDTO> getAllClientes() {
        List<ClienteResponseDTO> lista = cr.findAll().stream().map(ClienteResponseDTO::new).toList();
        return lista;
    }

    @PostMapping("/register")
    public void saveCliente(@RequestBody ClienteRequestDTO data) {
        Clientes cs = new Clientes(data);
        cr.save(cs);
    }
}
