package com.lucas.imobiliaria.model.domain.imovel;

import com.lucas.imobiliaria.model.domain.imovel.imagensImovel.ImagensImovel;

import java.time.LocalDate;
import java.util.List;

public record ImovelRequestDTO(String nome, String rua, String numero, String complemento, String cep, String cidade, String estado, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio, List<ImagensImovel> imagensImoveis) {
}
