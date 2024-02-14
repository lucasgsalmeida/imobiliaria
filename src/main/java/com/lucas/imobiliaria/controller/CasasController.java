package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelDTO;
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
    public List<CasasAluguelDTO> getAll() {
        List<CasasAluguelDTO> lista = casasAluguel.findAll().stream().map(CasasAluguelDTO::new).toList();
        return lista;
}

@PostMapping
    public void saveCasasAluguel(@RequestBody CasasAluguelRequestDTO data) {
        CasasAluguel cs = new CasasAluguel(data);
        casasAluguel.save(cs);
        return;
    }
}


