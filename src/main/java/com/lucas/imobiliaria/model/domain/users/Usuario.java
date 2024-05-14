package com.lucas.imobiliaria.model.domain.users;

import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Long idCliente;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;

    public Usuario() {
    }

    public Usuario(Long idCliente, String nome, String email, String senha, UserRole role) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(String nome, String email, String senha, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        if (this.role == UserRole.MASTER) {
            return List.of(new SimpleGrantedAuthority("ROLE_MASTER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (this.role == UserRole.USER) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
