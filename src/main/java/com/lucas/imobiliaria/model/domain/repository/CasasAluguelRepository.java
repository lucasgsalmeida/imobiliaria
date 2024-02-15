package com.lucas.imobiliaria.model.domain.repository;

import com.lucas.imobiliaria.model.domain.aluguel.CasasAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasasAluguelRepository extends JpaRepository<CasasAluguel, Integer> {
}
