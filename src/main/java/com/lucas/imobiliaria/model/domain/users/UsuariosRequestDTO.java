package com.lucas.imobiliaria.model.domain.users;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;

public record UsuariosRequestDTO (Long idCliente, String nome, String email, String senha, UserRole role){
}
