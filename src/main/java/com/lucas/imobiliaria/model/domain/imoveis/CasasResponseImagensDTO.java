package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CasasResponseImagensDTO {

    private CasasResponseDTO casaAluguel;
    private List<ImagensResponseDTO> imagens;

    public CasasResponseImagensDTO(CasasResponseDTO casaAluguel, List<ImagensResponseDTO> imagens) {
        this.casaAluguel = casaAluguel;
        this.imagens = imagens;
    }

}
