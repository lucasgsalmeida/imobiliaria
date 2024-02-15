package com.lucas.imobiliaria.model.domain.cliente.repository;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
