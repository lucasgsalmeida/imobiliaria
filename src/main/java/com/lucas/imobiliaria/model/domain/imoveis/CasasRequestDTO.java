package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensRequestDTO;

import java.time.LocalDate;
import java.util.List;

public record CasasRequestDTO(String rua, String numero, String complemento, String cep, String cidade, String estado, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio, List<ImagensImoveis> imagensImoveis) {
}
