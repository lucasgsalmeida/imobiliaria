package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.imoveis.CasasRequestDTO;
import com.lucas.imobiliaria.model.domain.imoveis.CasasResponseDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasRepository;
import com.lucas.imobiliaria.model.domain.repository.ImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CasasService {

    @Autowired
    private CasasRepository casasAluguel;

    @Autowired
    private ImagensRepository imgRepository;


    public ResponseEntity getCasaById(Long idCasa, Long idCliente) {

        CasasResponseDTO casaDto = casasAluguel.findCasaByIdAndCliente(idCasa, idCliente);
        return ResponseEntity.ok(casaDto);
    }

    public ResponseEntity getAll() {
        List<CasasResponseDTO> lista = casasAluguel.findAll().stream().map(CasasResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    public ResponseEntity register(CasasRequestDTO registroDTO) {
        if (registroDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        Casas casa = new Casas(registroDTO);
        imgRepository.saveAll(registroDTO.imagensImoveis());
        casasAluguel.save(casa);
        return ResponseEntity.ok().build();
    }


    private List<ImagensResponseDTO> getImagem(Long id) {
        List<ImagensResponseDTO> lista = imgRepository.findById(id).stream().map(ImagensResponseDTO::new).toList();
        return lista;
    }

}
