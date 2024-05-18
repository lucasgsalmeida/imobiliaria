package com.lucas.imobiliaria.model.domain.imovel.imagensImovel;

public record ImagensResponseDTO(Long id, String url) {
    public ImagensResponseDTO(ImagensImovel imagensImoveis) {
        this(imagensImoveis.getId(), imagensImoveis.getUrl());
    }
}





