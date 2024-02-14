package com.lucas.imobiliaria.model.domain.aluguel;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import jakarta.persistence.*;

@Table(name = "imagens")
@Entity(name = "imagens")
public class Imagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "casa_aluguel_id") // nome da coluna que contém a chave estrangeira
    private CasasAluguel casa_id;
    private String url;


}
