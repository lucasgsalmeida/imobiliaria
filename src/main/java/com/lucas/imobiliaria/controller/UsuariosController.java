package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.infra.security.TokenService;
import com.lucas.imobiliaria.model.domain.repository.ClienteRepository;
import com.lucas.imobiliaria.model.domain.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.LoginResponseDTO;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.domain.users.UsuariosRequestDTO;
import com.lucas.imobiliaria.model.domain.users.UsuariosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UsuariosController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/user")
    public ResponseEntity<UsuariosResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        Usuarios user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UsuariosResponseDTO resposta = new UsuariosResponseDTO(user);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuariosResponseDTO data) {
        var userpw = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);

        var token = tokenService.gerarToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UsuariosRequestDTO data) {
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }


        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios users = new Usuarios(clienteRepository.getById(data.idCliente()), data.email(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }
}
