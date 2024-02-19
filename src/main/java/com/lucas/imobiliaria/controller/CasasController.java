package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.imoveis.CasasResponseDTO;
import com.lucas.imobiliaria.model.domain.imoveis.CasasRequestDTO;
import com.lucas.imobiliaria.model.domain.imoveis.CasasResponseImagensDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRepository;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("casas")
public class CasasController {

    @Autowired
    private CasasRepository casasAluguel;

    @Autowired
    private ImagensRepository imgRepository;

    @GetMapping
    public List<CasasResponseImagensDTO> getAll() {
        List<CasasResponseImagensDTO> lista = new ArrayList<>();

        casasAluguel.findAll().forEach(casa -> {
            List<ImagensResponseDTO> imagens = getImagem(casa.getId());
            CasasResponseDTO casasAluguelDTO = new CasasResponseDTO(casa);
            lista.add(new CasasResponseImagensDTO(casasAluguelDTO, imagens));
        });

        return lista;
    }

    @PostMapping("/register")
    public void saveCasasAluguel(@RequestBody CasasRequestDTO data) {
        Casas cs = new Casas(data);
        casasAluguel.save(cs);
        return;
    }

    private List<ImagensResponseDTO> getImagem(Long id) {
        List<ImagensResponseDTO> lista = imgRepository.findById(id).stream().map(ImagensResponseDTO::new).toList();
        return lista;
    }
}


