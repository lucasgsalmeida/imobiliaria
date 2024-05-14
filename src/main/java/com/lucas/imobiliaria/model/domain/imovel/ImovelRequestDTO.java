package com.lucas.imobiliaria.model.domain.imovel;

import com.lucas.imobiliaria.model.domain.imovel.imagensImoveis.ImagensImoveis;

import java.time.LocalDate;
import java.util.List;

public record ImovelRequestDTO(String nome, String rua, String numero, String complemento, String cep, String cidade, String estado, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio, List<ImagensImoveis> imagensImoveis) {
}
