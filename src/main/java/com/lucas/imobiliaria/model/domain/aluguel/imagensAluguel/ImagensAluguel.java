package com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagens_aluguel")
@Getter
@Setter
public class ImagensAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "casa_aluguel_id")
    private CasasAluguel casaAluguel;

    @Column(name = "url")
    private String url;

}
