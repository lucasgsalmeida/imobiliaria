package com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ListObrigacao")
@AllArgsConstructor
@NoArgsConstructor
public class ListObrigacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table="obrigacao", name="id_obrigacao", referencedColumnName="id")
    private Long idObrigacao;

    @JoinColumn(table="cliente", name="id_cliente", referencedColumnName="id")
    private Long idCliente;

    @JoinColumn(table = "imovel", name="id_imovel", referencedColumnName="id")
    private Long idImovel;

    private Date data;

    private Status status;

    public ListObrigacao(ListRequestDTO data) {
        this.idObrigacao = data.idObrigacao();
        this.idImovel = data.idImovel();
        this.data = data.data();
        this.status = data.status();
    }
}
