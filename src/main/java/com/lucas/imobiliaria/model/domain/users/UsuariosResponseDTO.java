package com.lucas.imobiliaria.model.domain.users;

public record UsuariosResponseDTO(Integer id, String email, String senha, UserRole role) {

    public UsuariosResponseDTO(Usuarios user) {
        this(user.getId(), user.getEmail(), user.getSenha(), user.getRole());
    }
}
