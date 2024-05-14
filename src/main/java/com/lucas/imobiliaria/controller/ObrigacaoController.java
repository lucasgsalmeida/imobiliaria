package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.obrigacao.ObrigacaoRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacao.ObrigacaoResponseDTO;
import com.lucas.imobiliaria.service.ObrigacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("obrigacao")
public class ObrigacaoController {

    @Autowired
    private ObrigacaoService service;

    @PostMapping("/create")
    public ResponseEntity register(@RequestBody ObrigacaoRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
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
    public ResponseEntity update(@RequestBody ObrigacaoResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.update(data, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ObrigacaoResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.delete(data, userDetails);
    }
}

