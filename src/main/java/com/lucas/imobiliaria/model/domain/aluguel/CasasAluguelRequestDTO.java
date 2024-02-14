package com.lucas.imobiliaria.model.domain.aluguel;

import java.time.LocalDate;

public record CasasAluguelRequestDTO(String rua, String numero, String complemento, String cep, String cidade, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao) {
}
