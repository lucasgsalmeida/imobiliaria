package com.lucas.imobiliaria.model.domain.imovel.imagensImoveis;

public record ImagensResponseDTO(Long id, String url) {
    public ImagensResponseDTO(ImagensImoveis imagensImoveis) {
        this(imagensImoveis.getId(), imagensImoveis.getUrl());
    }
}





