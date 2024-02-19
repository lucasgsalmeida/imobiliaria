package com.lucas.imobiliaria.model.domain.repository;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
