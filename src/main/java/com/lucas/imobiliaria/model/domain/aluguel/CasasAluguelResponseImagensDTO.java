package com.lucas.imobiliaria.model.domain.aluguel;

import com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel.ImagensResponseDTO;

import java.util.List;

public class CasasAluguelResponseImagensDTO {

    private CasasAluguelResponseDTO casaAluguel;
    private List<ImagensResponseDTO> imagens;

    public CasasAluguelResponseImagensDTO(CasasAluguelResponseDTO casaAluguel, List<ImagensResponseDTO> imagens) {
        this.casaAluguel = casaAluguel;
        this.imagens = imagens;
    }

}
