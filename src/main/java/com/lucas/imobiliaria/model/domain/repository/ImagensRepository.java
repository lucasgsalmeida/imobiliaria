package com.lucas.imobiliaria.model.domain.repository;

import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagensRepository extends JpaRepository<ImagensImoveis, Long> {

  //  @Query("SELECT img FROM ImagensImoveis img WHERE img.casa = :casa and img.idCliente = :idCliente")
   // List<ImagensResponseDTO> findAllByCasaId(@Param("casa") Long casa, @Param("idCliente") Long idCliente);

}
