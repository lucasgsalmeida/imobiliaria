package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;
import com.lucas.imobiliaria.model.domain.obrigacoes.ObrigacoesResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObrigacoesRepository extends JpaRepository<Obrigacoes, Long> {

    @Query("SELECT obr FROM Obrigacoes obr WHERE obr.id = :id and obr.idCliente = :idCliente")
    Obrigacoes findObrigacoesByIdAndCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT obr FROM Obrigacoes obr WHERE obr.idCliente = :idCliente")
    List<ObrigacoesResponseDTO> findObrigacoesByCliente(@Param("idCliente") Long idCliente);
}
