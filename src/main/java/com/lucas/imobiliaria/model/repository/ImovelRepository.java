package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.imovel.Imovel;
import com.lucas.imobiliaria.model.domain.imovel.ImovelResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    @Query("SELECT imovel FROM Imovel imovel WHERE imovel.id = :id and imovel.idCliente = :idCliente")
    Imovel findCasaByIdAndCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT imovel FROM Imovel imovel WHERE imovel.idCliente = :idCliente")
    List<ImovelResponseDTO> findCasaByCliente(@Param("idCliente") Long idCliente);

}
