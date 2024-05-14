package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.obrigacao.Obrigacao;
import com.lucas.imobiliaria.model.domain.obrigacao.ObrigacaoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObrigacaoRepository extends JpaRepository<Obrigacao, Long> {

    @Query("SELECT obr FROM Obrigacao obr WHERE obr.id = :id and obr.idCliente = :idCliente")
    Obrigacao findObrigacaoByIdAndCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT obr FROM Obrigacao obr WHERE obr.idCliente = :idCliente")
    List<ObrigacaoResponseDTO> findObrigacaoByCliente(@Param("idCliente") Long idCliente);
}
