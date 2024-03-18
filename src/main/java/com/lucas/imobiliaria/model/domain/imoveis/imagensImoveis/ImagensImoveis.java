package com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.imoveis.ImoveisDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagens_imoveis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagensImoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "casa_id")
    private Long casa;

    @Column(name = "url")
    private String url;

    public ImagensImoveis(Casas casas, ImagensRequestDTO img) {
        this.casa = casas.getId();
        this.url = img.url();
    }

}
