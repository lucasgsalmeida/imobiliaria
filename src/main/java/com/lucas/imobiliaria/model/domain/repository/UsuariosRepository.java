package com.lucas.imobiliaria.model.domain.repository;

import com.lucas.imobiliaria.model.domain.users.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    UserDetails findByEmail(String email);
}
