package com.lucas.imobiliaria.model.domain.users;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;

public record UsuarioRequestDTO (Long idCliente, String nome, String email, String senha, UserRole role){
}
