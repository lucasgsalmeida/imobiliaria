package com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao;

import com.lucas.imobiliaria.model.domain.obrigacao.Obrigacao;

import java.util.Date;

public record ListResponseDTO(Long id , Obrigacao obrigacoes, Long idCliente, Date data, Status status) {

    public ListResponseDTO(Long id, Obrigacao obrigacoes, Long idCliente, Date data, Status status) {
        this.id = id;
        this.obrigacoes = obrigacoes;
        this.idCliente = idCliente;
        this.data = data;
        this.status = status;
    }
}
