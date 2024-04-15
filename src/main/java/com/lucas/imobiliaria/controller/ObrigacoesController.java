package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.obrigacoes.ObrigacoesRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacoes.ObrigacoesResponseDTO;
import com.lucas.imobiliaria.service.ObrigacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("obrigacoes")
public class ObrigacoesController {

    @Autowired
    private ObrigacoesService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ObrigacoesRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.register(data, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAll(userDetails);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@RequestParam(name = "id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getById(id, userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ObrigacoesResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.update(data, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ObrigacoesResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.delete(data, userDetails);
    }
}

