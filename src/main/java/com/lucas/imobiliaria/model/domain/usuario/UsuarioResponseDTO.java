package com.lucas.imobiliaria.model.domain.usuario;

public record UsuarioResponseDTO(Long id, Long idCliente, String nome, String email, String senha, UserRole role) {

    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(), user.getIdCliente(), user.getNome(), user.getEmail(), user.getSenha(), user.getRole());
    }
}
