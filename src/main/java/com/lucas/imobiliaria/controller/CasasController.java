package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelResponseDTO;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelRequestDTO;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelResponseImagensDTO;
import com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel.ImagensAluguel;
import com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel.ImagensRepository;
import com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasAluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("casas")
public class CasasController {

    @Autowired
    private CasasAluguelRepository casasAluguel;

    @Autowired
    private ImagensRepository imgRepository;

    @GetMapping
    public List<CasasAluguelResponseImagensDTO> getAll() {
        List<CasasAluguelResponseImagensDTO> lista = new ArrayList<>();

        casasAluguel.findAll().forEach(casa -> {
            List<ImagensResponseDTO> imagens = getImagem(casa.getId());
            CasasAluguelResponseDTO casasAluguelDTO = new CasasAluguelResponseDTO(casa);
            lista.add(new CasasAluguelResponseImagensDTO(casasAluguelDTO, imagens));
        });

        return lista;
    }

    @PostMapping("/register")
    public void saveCasasAluguel(@RequestBody CasasAluguelRequestDTO data) {
        CasasAluguel cs = new CasasAluguel(data);
        casasAluguel.save(cs);
        return;
    }

    private List<ImagensResponseDTO> getImagem(Integer id) {
        List<ImagensResponseDTO> lista = imgRepository.findById(id).stream().map(ImagensResponseDTO::new).toList();
        return lista;
    }
}


