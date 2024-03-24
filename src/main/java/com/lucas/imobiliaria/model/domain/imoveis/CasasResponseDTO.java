package com.lucas.imobiliaria.model.domain.imoveis;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;

import java.time.LocalDate;
import java.util.List;

public record CasasResponseDTO(Long id, Long idCliente, String rua, String numero, String complemento, String cep, String cidade, String estado, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio, List<ImagensImoveis> imagensImoveis) {

public CasasResponseDTO(Casas casas) {
    this(casas.getId(), casas.getIdCliente(), casas.getRua(), casas.getNumero(), casas.getComplemento(), casas.getCep(), casas.getCidade(), casas.getEstado(), casas.getBairro(), casas.getNumeroQuartos(), casas.getNumeroBanheiros(), casas.getPrecoAluguel(), casas.isMobiliada(), casas.isDisponivel(), casas.getDataDisponibilidade(), casas.getDescricao(), casas.getTipoAnuncio(), casas.getImagensImoveis());
}
}
