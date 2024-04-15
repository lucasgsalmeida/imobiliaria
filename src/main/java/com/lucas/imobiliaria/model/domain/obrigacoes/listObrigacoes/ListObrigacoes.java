package com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes;

import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ListObrigacoes")
@AllArgsConstructor
@NoArgsConstructor
public class ListObrigacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table="obrigacoes", name="id_obrigacao", referencedColumnName="id")
    private Long idObrigacao;

    @JoinColumn(table="clientes", name="id_cliente", referencedColumnName="id")
    private Long idCliente;

    private Date data;

    private Status status;

    public ListObrigacoes(ListRequestDTO data) {
        this.idObrigacao = data.idObrigacao();
        this.data = data.data();
        this.status = data.status();
    }
}
