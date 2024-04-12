package com.lucas.imobiliaria.model.domain.users;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;

public record UsuariosResponseDTO(Long id, Long idCliente, String email, String senha, UserRole role) {

    public UsuariosResponseDTO(Usuarios user) {
        this(user.getId(), user.getIdCliente(), user.getEmail(), user.getSenha(), user.getRole());
    }
}
