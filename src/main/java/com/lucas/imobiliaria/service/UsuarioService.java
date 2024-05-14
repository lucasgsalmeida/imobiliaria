package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.ClienteStateCache;
import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.cliente.ClienteResponseDTO;
import com.lucas.imobiliaria.model.domain.cliente.Cliente;
import com.lucas.imobiliaria.model.domain.users.*;
import com.lucas.imobiliaria.model.repository.ClienteRepository;
import com.lucas.imobiliaria.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UserStateCache userStateCache;

    @Autowired
    private ClienteStateCache clienteStateCache;

    @Autowired
    private ClienteRepository clienteRepository;


    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        Usuario user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioResponseDTO resposta = new UsuarioResponseDTO(user);
        return ResponseEntity.ok(resposta);
    }

    public ResponseEntity login(UsuarioResponseDTO data) {

        var userpw = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        userStateCache.saveUserState(data.email(), (Usuario) auth.getPrincipal());

        if (((Usuario) auth.getPrincipal()).getRole() != UserRole.MASTER) {
            Cliente cli = clienteRepository.getReferenceById(((Usuario) auth.getPrincipal()).getIdCliente());
            clienteStateCache.saveClienteState(cli.getId(), cli);
        }

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity register(UsuarioRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        if (data.role().equals(UserRole.MASTER)) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuario users = new Usuario(data.idCliente(), data.nome(), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity registerAdministrator(UsuarioRequestDTO data, UserDetails userDetails) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        if (data.role().equals(UserRole.MASTER)) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuario users = new Usuario(data.idCliente(), data.nome(), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }


    /*

    Essa função abaixo registra um usuario MASTER, o usuario que registra novos clientes.

     */
    public ResponseEntity registerMaster(UsuarioRequestDTO data) {

        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        if (data.idCliente() != 15032003) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuario users = new Usuario(null, data.nome(), data.email(), encPass, UserRole.MASTER);

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getUsuarioAndCliente(UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());
        Cliente clientes = clienteRepository.getById(user.getIdCliente());

        System.out.println(user.toString());
        System.out.println(clientes.toString());


        user.setSenha("**");
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(user);
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
                Usuario user = (Usuario) repository.findByEmail(tokenService.extractUsername(token));
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
