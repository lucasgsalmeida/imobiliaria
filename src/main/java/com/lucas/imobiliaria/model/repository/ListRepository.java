package com.lucas.imobiliaria.model.repository;

import com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao.ListObrigacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListObrigacao, Long> {
}
