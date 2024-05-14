package com.lucas.imobiliaria.model.domain.obrigacao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Obrigacao")
@AllArgsConstructor
@NoArgsConstructor
public class Obrigacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table="clientes", name="id_cliente", referencedColumnName="id")
    private Long idCliente;

    private String nome;
    private Boolean isRecorrente;
    private Recorrencia recorrencia;
    private Date diaVencimento;

    public Obrigacao(ObrigacaoRequestDTO dto) {
        this.nome = dto.nome();
        this.isRecorrente = dto.isRecorrente();
        this.recorrencia = dto.recorrencia();
        this.diaVencimento = dto.diaVencimento();
    }
}
