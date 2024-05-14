package com.lucas.imobiliaria.model.domain.obrigacao;

import java.util.Date;

public record ObrigacaoResponseDTO(Long id, Long idCliente, String nome, Boolean isRecorrente, Recorrencia recorrencia, Date diaVencimento) {

    public ObrigacaoResponseDTO(Obrigacao obrigacoes) {
        this(obrigacoes.getId(), obrigacoes.getIdCliente(), obrigacoes.getNome(), obrigacoes.getIsRecorrente(), obrigacoes.getRecorrencia(), obrigacoes.getDiaVencimento());
    }
}
