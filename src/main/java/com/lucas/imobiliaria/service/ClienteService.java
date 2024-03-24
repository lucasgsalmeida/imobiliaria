package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.model.domain.cliente.ClienteResponseDTO;
import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import com.lucas.imobiliaria.model.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cr;

    public ResponseEntity getClienteById(@PathVariable Long id) {
        Clientes clienteProcurado = cr.findById(id).orElseThrow();

        if (clienteProcurado != null) {
            ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteProcurado);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity getAllClientes() {
        List<ClienteResponseDTO> lista = cr.findAll().stream().map(ClienteResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @Transactional
    public ResponseEntity saveCliente(@RequestBody ClienteRequestDTO data) {
        Clientes cs = new Clientes(data);

        if (cs != null) {
            cr.save(cs);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
