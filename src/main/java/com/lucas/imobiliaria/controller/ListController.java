package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao.ListRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao.ListResponseDTO;
import com.lucas.imobiliaria.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("listobrigacao")
public class ListController {

    @Autowired
    private ListService service;

    @PostMapping("/create")
    public ResponseEntity register(@RequestBody ListRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
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
    public ResponseEntity update(@RequestBody ListResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.update(data, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ListResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.delete(data, userDetails);
    }

}

