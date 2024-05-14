package com.lucas.imobiliaria.model.domain.obrigacao;

import java.util.Date;

public record ObrigacaoRequestDTO(String nome, Boolean isRecorrente, Recorrencia recorrencia, Date diaVencimento) {
}
