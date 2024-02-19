package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;

import java.util.List;

public class CasasResponseImagensDTO {

    private CasasResponseDTO casaAluguel;
    private List<ImagensResponseDTO> imagens;

    public CasasResponseImagensDTO(CasasResponseDTO casaAluguel, List<ImagensResponseDTO> imagens) {
        this.casaAluguel = casaAluguel;
        this.imagens = imagens;
    }

}
