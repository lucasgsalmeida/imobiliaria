package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.model.domain.imoveis.*;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRepository;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRequestDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("casas")
public class CasasController {

    @Autowired
    private CasasRepository casasAluguel;

    @Autowired
    private ImagensRepository imgRepository;

    @GetMapping("/get/all")
    public List<CasasResponseImagensDTO> getAll() {
        List<CasasResponseImagensDTO> lista = new ArrayList<>();

        casasAluguel.findAll().forEach(casa -> {
            List<ImagensResponseDTO> imagensImoveis = imgRepository.findAllByCasaId(casa.getId());
            CasasResponseDTO casasAluguelDTO = new CasasResponseDTO(casa);
            lista.add(new CasasResponseImagensDTO(casasAluguelDTO, imagensImoveis));
        });
        return lista;
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ImoveisDTO registroDTO) {
        if (registroDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        CasasRequestDTO data = registroDTO.casas();
        Casas casa = new Casas(data);

        if (data != null) {
            casasAluguel.save(casa);
        }

        List<ImagensRequestDTO> imagensDto = registroDTO.imagens();
        if (imagensDto != null) {
            for (ImagensRequestDTO imgs : imagensDto) {
                ImagensImoveis imovel = new ImagensImoveis(casa, imgs);
                imgRepository.save(imovel);
            }
        }

        return ResponseEntity.ok().build();
    }


    private List<ImagensResponseDTO> getImagem(Long id) {
        List<ImagensResponseDTO> lista = imgRepository.findById(id).stream().map(ImagensResponseDTO::new).toList();
        return lista;
    }
}


