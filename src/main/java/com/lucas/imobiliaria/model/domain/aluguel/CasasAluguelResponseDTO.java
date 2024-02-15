package com.lucas.imobiliaria.model.domain.aluguel;

import java.time.LocalDate;

public record CasasAluguelResponseDTO(Integer id, String rua, String numero, String complemento, String cep, String cidade, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao) {

public CasasAluguelResponseDTO(CasasAluguel casasAluguel) {
    this(casasAluguel.getId(), casasAluguel.getRua(), casasAluguel.getNumero(), casasAluguel.getComplemento(), casasAluguel.getCep(), casasAluguel.getCidade(), casasAluguel.getBairro(), casasAluguel.getNumeroQuartos(), casasAluguel.getNumeroBanheiros(), casasAluguel.getPrecoAluguel(), casasAluguel.isMobiliada(), casasAluguel.isDisponivel(), casasAluguel.getDataDisponibilidade(), casasAluguel.getDescricao());
}
}
