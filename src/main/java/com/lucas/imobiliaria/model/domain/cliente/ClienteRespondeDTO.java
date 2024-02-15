package com.lucas.imobiliaria.model.domain.cliente;

public record ClienteRespondeDTO(Integer id, String nome, String cnpj) {

    public ClienteRespondeDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCnpj());
    }

}
