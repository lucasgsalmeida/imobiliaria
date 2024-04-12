package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.ClienteStateCache;
import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.infra.security.TokenService;
import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import com.lucas.imobiliaria.model.domain.repository.ClienteRepository;
import com.lucas.imobiliaria.model.domain.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.LoginResponseDTO;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.domain.users.UsuariosRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuariosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UsuariosService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private UserStateCache userStateCache;

    @Autowired
    private ClienteStateCache clienteStateCache;

    @Autowired ClienteRepository clienteRepository;

    public ResponseEntity<UsuariosResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        Usuarios user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsuariosResponseDTO resposta = new UsuariosResponseDTO(user);
        return ResponseEntity.ok(resposta);
    }

    public ResponseEntity login(@RequestBody @Validated UsuariosResponseDTO data) {
        var userpw = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);

        var token = tokenService.gerarToken((Usuarios) auth.getPrincipal());

        userStateCache.saveUserState(data.email(), (Usuarios) auth.getPrincipal());
        Clientes cli = clienteRepository.getById(data.idCliente());
        clienteStateCache.saveClienteState(cli.getId(), cli);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity register(UsuariosRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(user.getIdCliente(), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity registerAdministrator(UsuariosRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(data.idCliente(), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

}
