package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.imovel.imagensImovel.ImagensImoveis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagensRepository extends JpaRepository<ImagensImoveis, Long> {

  //  @Query("SELECT img FROM ImagensImoveis img WHERE img.casa = :casa and img.idCliente = :idCliente")
   // List<ImagensResponseDTO> findAllByCasaId(@Param("casa") Long casa, @Param("idCliente") Long idCliente);

}
