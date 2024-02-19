package com.lucas.imobiliaria.model.domain.cliente;

public record ClienteResponseDTO(Long id, String nome, String cnpj) {

    public ClienteResponseDTO(Clientes clientes) {
        this(clientes.getId(), clientes.getNome(), clientes.getCnpj());
    }

}
