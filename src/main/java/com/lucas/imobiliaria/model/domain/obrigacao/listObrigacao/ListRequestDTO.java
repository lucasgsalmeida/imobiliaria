package com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao;


import java.util.Date;

public record ListRequestDTO(Long idObrigacao, Long idImovel, Date data, Status status) {
}
