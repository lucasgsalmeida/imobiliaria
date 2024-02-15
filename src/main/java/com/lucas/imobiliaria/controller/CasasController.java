package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelResponseDTO;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelRequestDTO;
import com.lucas.imobiliaria.model.domain.aluguel.repository.CasasAluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("casas")
public class CasasController {

@Autowired
private CasasAluguelRepository casasAluguel;

@GetMapping
    public List<CasasAluguelResponseDTO> getAll() {
        List<CasasAluguelResponseDTO> lista = casasAluguel.findAll().stream().map(CasasAluguelResponseDTO::new).toList();
        return lista;
}

@PostMapping
    public void saveCasasAluguel(@RequestBody CasasAluguelRequestDTO data) {
        CasasAluguel cs = new CasasAluguel(data);
        casasAluguel.save(cs);
        return;
    }
}


