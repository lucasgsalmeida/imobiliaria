package com.lucas.imobiliaria.model.domain.aluguel;

import java.io.Serializable;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CasasAluguelId implements Serializable {

    private Integer id;
    private Integer id_cliente;
}
