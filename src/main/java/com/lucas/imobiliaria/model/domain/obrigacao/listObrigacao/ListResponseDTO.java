package com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao;

import com.lucas.imobiliaria.model.domain.obrigacao.Obrigacao;

import java.util.Date;

public record ListResponseDTO(Long id , Long idObrigacao, Long idCliente, Long idImovel, Date data, Status status) {

    public ListResponseDTO(ListObrigacao list) {
        this(list.getId(), list.getIdObrigacao(), list.getIdCliente(), list.getIdImovel(), list.getData(), list.getStatus());
    }
}
