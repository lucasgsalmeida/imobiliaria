package com.lucas.imobiliaria.model.domain.imovel;

import com.lucas.imobiliaria.model.domain.imovel.imagensImovel.ImagensImoveis;

import java.time.LocalDate;
import java.util.List;

public record ImovelResponseDTO(Long id, Long idCliente, String nome, String rua, String numero, String complemento, String cep, String cidade, String estado, String bairro, int numeroQuartos, int numeroBanheiros, double precoAluguel, boolean mobiliada, boolean disponivel, LocalDate dataDisponibilidade, String descricao, String tipoAnuncio, List<ImagensImoveis> imagensImoveis) {

public ImovelResponseDTO(Imovel imovel) {
    this(imovel.getId(), imovel.getIdCliente(), imovel.getNome(), imovel.getRua(), imovel.getNumero(), imovel.getComplemento(), imovel.getCep(), imovel.getCidade(), imovel.getEstado(), imovel.getBairro(), imovel.getNumeroQuartos(), imovel.getNumeroBanheiros(), imovel.getPrecoAluguel(), imovel.isMobiliada(), imovel.isDisponivel(), imovel.getDataDisponibilidade(), imovel.getDescricao(), imovel.getTipoAnuncio(), imovel.getImagensImoveis());
}
}
