package com.lucas.imobiliaria.model.domain.repository;

import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasasRepository extends JpaRepository<Casas, Long> {
}
