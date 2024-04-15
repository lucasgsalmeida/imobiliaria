package com.lucas.imobiliaria.model.domain.obrigacoes;

import java.util.Date;

public record ObrigacoesResponseDTO(Long id, Long idCliente, String nome, Boolean isRecorrente, Recorrencia recorrencia, Date diaVencimento) {

    public ObrigacoesResponseDTO(Obrigacoes obrigacoes) {
        this(obrigacoes.getId(), obrigacoes.getIdCliente(), obrigacoes.getNome(), obrigacoes.getIsRecorrente(), obrigacoes.getRecorrencia(), obrigacoes.getDiaVencimento());
    }
}
