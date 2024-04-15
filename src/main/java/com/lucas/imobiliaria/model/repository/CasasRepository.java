package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.imoveis.CasasResponseDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CasasRepository extends JpaRepository<Casas, Long> {

    @Query("SELECT casas FROM Casas casas WHERE casas.id = :id and casas.idCliente = :idCliente")
    Casas findCasaByIdAndCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT casas FROM Casas casas WHERE casas.idCliente = :idCliente")
    List<CasasResponseDTO> findCasaByCliente(@Param("idCliente") Long idCliente);

}
