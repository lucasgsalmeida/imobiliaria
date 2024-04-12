package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.imoveis.*;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.repository.ImagensRepository;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRequestDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasRepository;
import com.lucas.imobiliaria.service.CasasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("casas")
public class CasasController {

    @Autowired
    private CasasService casasService;

    /*
    @GetMapping("/get")
    public CasasResponseImagensDTO getCasaById(@RequestParam(name="casa") Long idCasa, @RequestParam(name="cliente") Long idCliente) {
        return casasService.getCasaById(idCasa, idCliente);
    }
  */
    @GetMapping("/get/all")
    public ResponseEntity getAll() {
        return casasService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CasasRequestDTO registroDTO, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        return casasService.register(registroDTO, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody CasasResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return casasService.delete(data, userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CasasResponseDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return casasService.update(data, userDetails);
    }
}


