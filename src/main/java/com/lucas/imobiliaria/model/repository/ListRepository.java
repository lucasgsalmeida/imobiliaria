package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.imovel.ImovelResponseDTO;
import com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao.ListObrigacao;
import com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao.ListResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListRepository extends JpaRepository<ListObrigacao, Long> {

    @Query("SELECT lists FROM ListObrigacao lists WHERE lists.idCliente = :idCliente")
    List<ListResponseDTO> findAllByIdCliente(@Param("idCliente") Long idCliente);

}
