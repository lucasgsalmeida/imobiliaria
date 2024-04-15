package com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes;

import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;

import java.util.Date;

public record ListRequestDTO(Long idObrigacao, Date data, Status status) {
}
