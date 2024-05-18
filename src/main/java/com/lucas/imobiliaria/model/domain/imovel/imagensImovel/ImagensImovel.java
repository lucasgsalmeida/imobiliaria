package com.lucas.imobiliaria.model.domain.imovel.imagensImovel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagens_imovel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagensImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    public ImagensImovel(ImagensRequestDTO img) {
        this.url = img.url();
    }

}
