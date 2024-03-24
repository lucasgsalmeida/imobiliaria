package com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis;

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

    @Column(name = "url")
    private String url;

    public ImagensImoveis(ImagensRequestDTO img) {
        this.url = img.url();
    }

}
