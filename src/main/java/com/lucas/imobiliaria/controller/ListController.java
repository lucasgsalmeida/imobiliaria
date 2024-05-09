package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListResponseDTO;
import com.lucas.imobiliaria.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("list")
public class ListController {

    @Autowired
    private ListService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ListRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.register(data, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@RequestParam(name = "id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getById(id, userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ListResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.update(data, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ListResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.delete(data, userDetails);
    }

}
