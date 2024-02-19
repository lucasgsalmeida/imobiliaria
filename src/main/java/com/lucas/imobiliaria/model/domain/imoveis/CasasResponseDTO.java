package com.lucas.imobiliaria.model.domain.imoveis;

import java.time.LocalDate;

public record CasasResponseDTO(Long id, String rua, String numero, String complemento, String cep, String cidade, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio) {

public CasasResponseDTO(Casas casas) {
    this(casas.getId(), casas.getRua(), casas.getNumero(), casas.getComplemento(), casas.getCep(), casas.getCidade(), casas.getBairro(), casas.getNumeroQuartos(), casas.getNumeroBanheiros(), casas.getPrecoAluguel(), casas.isMobiliada(), casas.isDisponivel(), casas.getDataDisponibilidade(), casas.getDescricao(), casas.getTipoAnuncio());
}
}
