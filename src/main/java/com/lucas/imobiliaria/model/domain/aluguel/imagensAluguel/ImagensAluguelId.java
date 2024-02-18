package com.lucas.imobiliaria.model.domain.aluguel.imagensAluguel;

import java.io.Serializable;
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
public class ImagensAluguelId implements Serializable {

    private Integer id;
    private Integer casa_aluguel_id;
}
