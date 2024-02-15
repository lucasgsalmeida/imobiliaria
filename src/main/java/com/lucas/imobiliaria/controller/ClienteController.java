package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.model.domain.cliente.ClienteRespondeDTO;
import com.lucas.imobiliaria.model.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository cr;

    @GetMapping("/id/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return cr.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + id));
    }

    @GetMapping("/all")
    public List<ClienteRespondeDTO> getAllClientes() {
        List<ClienteRespondeDTO> lista = cr.findAll().stream().map(ClienteRespondeDTO::new).toList();
        return lista;
    }

    @PostMapping
    public void saveCliente(@RequestBody ClienteRequestDTO data) {
        Cliente cs = new Cliente(data);
        cr.save(cs);
    }
}
