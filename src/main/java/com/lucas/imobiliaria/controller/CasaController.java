package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.imovel.*;
import com.lucas.imobiliaria.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("casas")
public class CasaController {

    @Autowired
    private ImovelService casasService;

    @GetMapping("/get")
    public ResponseEntity getCasaById(@RequestParam(name="casa") Long idCasa, @RequestParam(name="cliente") Long idCliente) {
        return casasService.getCasaById(idCasa, idCliente);
    }
    @GetMapping("/get/all")
    public ResponseEntity getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return casasService.getAll(userDetails);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ImovelRequestDTO registroDTO, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        return casasService.register(registroDTO, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ImovelResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return casasService.delete(data, userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ImovelResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return casasService.update(data, userDetails);
    }
}


