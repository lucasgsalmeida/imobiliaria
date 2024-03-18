package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

    public record ImoveisDTO(CasasRequestDTO casas, List<ImagensRequestDTO> imagens) {
}

