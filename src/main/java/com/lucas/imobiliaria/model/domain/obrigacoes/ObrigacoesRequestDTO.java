package com.lucas.imobiliaria.model.domain.obrigacoes;

import java.util.Date;

public record ObrigacoesRequestDTO(String nome, Boolean isRecorrente, Recorrencia recorrencia, Date diaVencimento) {
}
