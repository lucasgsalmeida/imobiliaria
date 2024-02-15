package com.lucas.imobiliaria.model.domain.cliente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Clientes")
@Entity(name = "Clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cnpj;

    public Cliente(Integer id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.nome = clienteRequestDTO.nome();
        this.cnpj = clienteRequestDTO.cnpj();
    }

    public Cliente() {

    }
}
