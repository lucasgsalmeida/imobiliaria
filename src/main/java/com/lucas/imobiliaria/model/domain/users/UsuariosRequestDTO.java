package com.lucas.imobiliaria.model.domain.users;

public record UsuariosRequestDTO (String email, String senha, UserRole role){
}
