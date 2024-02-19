package com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;

public record ImagensResponseDTO(Long id, Casas casas, String url) {
    public ImagensResponseDTO(ImagensImoveis imagensImoveis) {
        this(imagensImoveis.getId(), imagensImoveis.getCasa(), imagensImoveis.getUrl());
    }
}





