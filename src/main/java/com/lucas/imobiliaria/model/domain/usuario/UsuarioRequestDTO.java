package com.lucas.imobiliaria.model.domain.usuario;

public record UsuarioRequestDTO (Long idCliente, String nome, String email, String senha, UserRole role){
}
