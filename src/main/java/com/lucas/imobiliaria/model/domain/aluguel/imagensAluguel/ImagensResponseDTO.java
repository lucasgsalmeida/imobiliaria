package com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguelId;

public record ImagensResponseDTO(Integer id, CasasAluguel casa_id, String url) {

    public ImagensResponseDTO(ImagensAluguel img) {
        this(img.getId(), img.getCasaAluguel(), img.getUrl());
    }
}
