package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;
import com.lucas.imobiliaria.model.domain.obrigacoes.ObrigacoesRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacoes.ObrigacoesResponseDTO;
import com.lucas.imobiliaria.model.domain.obrigacoes.Recorrencia;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.repository.ObrigacoesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObrigacoesService {

    @Autowired
    private ObrigacoesRepository repository;

    @Autowired
    private UserStateCache userStateCache;

    @Autowired
    private ListService listService;

    public ResponseEntity register(ObrigacoesRequestDTO data, UserDetails userDetails) {

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        Obrigacoes obrigacoes = new Obrigacoes(data);
        obrigacoes.setIdCliente(user.getIdCliente());

        repository.save(obrigacoes);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAll(UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ObrigacoesResponseDTO> list = repository.findObrigacoesByCliente(user.getIdCliente());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity getById(Long id, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());
        Obrigacoes obri = repository.getById(id);

        if (obri.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ObrigacoesResponseDTO dto = new ObrigacoesResponseDTO(obri);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity update(ObrigacoesResponseDTO data, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (data.idCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Obrigacoes obrigacoes = repository.findObrigacoesByIdAndCliente(data.id(), user.getIdCliente());
        BeanUtils.copyProperties(data, obrigacoes);
        repository.save(obrigacoes);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity delete(ObrigacoesResponseDTO data, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Obrigacoes obrigacoes = repository.findObrigacoesByIdAndCliente(data.id(), user.getIdCliente());

        if (obrigacoes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(obrigacoes);
        return ResponseEntity.ok().build();
    }
}
