package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListObrigacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListObrigacoes, Long> {
}
