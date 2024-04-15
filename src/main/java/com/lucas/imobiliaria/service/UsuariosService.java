package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.ClienteStateCache;
import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.infra.security.TokenService;
import com.lucas.imobiliaria.model.domain.cliente.ClienteRequestDTO;
import com.lucas.imobiliaria.model.domain.cliente.ClienteResponseDTO;
import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import com.lucas.imobiliaria.model.repository.ClienteRepository;
import com.lucas.imobiliaria.model.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.LoginResponseDTO;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.domain.users.UsuariosRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuariosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<UsuariosResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        Usuarios user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsuariosResponseDTO resposta = new UsuariosResponseDTO(user);
        return ResponseEntity.ok(resposta);
    }

    public ResponseEntity login(UsuariosResponseDTO data) {
        var userpw = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);

        var token = tokenService.gerarToken((Usuarios) auth.getPrincipal());

        userStateCache.saveUserState(data.email(), (Usuarios) auth.getPrincipal());
        Clientes cli = clienteRepository.getReferenceById(((Usuarios) auth.getPrincipal()).getIdCliente());
        clienteStateCache.saveClienteState(cli.getId(), cli);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity register(UsuariosRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(user.getIdCliente(), data.nome(), data.email(), encPass, data.role());

        this.repository.save(users);

        Clientes cli = clienteRepository.getReferenceById(user.getIdCliente());
        clienteStateCache.saveClienteState(cli.getId(), cli);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity registerAdministrator(UsuariosRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(data.idCliente(), data.nome(), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

        public ResponseEntity<?> getUsuarioAndCliente(UserDetails userDetails) {
            Usuarios user = userStateCache.getUserState(userDetails.getUsername());
            Clientes clientes = clienteRepository.getById(user.getIdCliente());

            System.out.println(user.toString());
            System.out.println(clientes.toString());


            user.setSenha("**");
            UsuariosResponseDTO responseDTO = new UsuariosResponseDTO(user);
            ClienteResponseDTO clienteRequestDTO = new ClienteResponseDTO(clientes);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("usuario", responseDTO);
            responseMap.put("cliente", clienteRequestDTO);

            return ResponseEntity.ok(responseMap);
        }

    public ResponseEntity<String> verifyToken(String authorizationHeader) {
        String token = extractToken(authorizationHeader);

        try {
            if (token != null && tokenService.validateToken(token)) {
                Usuarios user = (Usuarios) repository.findByEmail(tokenService.extractUsername(token));
                userStateCache.saveUserState(user.getEmail(), user);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

}
