package com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes;

import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;
import java.util.Date;

public record ListResponseDTO(Long id , Obrigacoes obrigacoes, Long idCliente, Date data, Status status) {

    public ListResponseDTO(Long id, Obrigacoes obrigacoes, Long idCliente, Date data, Status status) {
        this.id = id;
        this.obrigacoes = obrigacoes;
        this.idCliente = idCliente;
        this.data = data;
        this.status = status;
    }
}
