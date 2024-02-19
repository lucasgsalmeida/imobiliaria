package com.lucas.imobiliaria.model.domain.users;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;

public record UsuariosRequestDTO (Long idCliente, String email, String senha, UserRole role){
}
