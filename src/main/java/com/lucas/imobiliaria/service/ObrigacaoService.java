package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.obrigacao.Obrigacao;
import com.lucas.imobiliaria.model.domain.obrigacao.ObrigacaoRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacao.ObrigacaoResponseDTO;
import com.lucas.imobiliaria.model.domain.usuario.Usuario;
import com.lucas.imobiliaria.model.repository.ObrigacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObrigacaoService {

    @Autowired
    private ObrigacaoRepository repository;

    @Autowired
    private UserStateCache userStateCache;

    @Autowired
    private ListService listService;

    public ResponseEntity register(ObrigacaoRequestDTO data, UserDetails userDetails) {

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        Obrigacao obrigacoes = new Obrigacao(data);
        obrigacoes.setIdCliente(user.getIdCliente());

        repository.save(obrigacoes);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAll(UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ObrigacaoResponseDTO> list = repository.findObrigacaoByCliente(user.getIdCliente());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity getById(Long id, UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());
        Obrigacao obri = repository.getById(id);

        if (obri.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ObrigacaoResponseDTO dto = new ObrigacaoResponseDTO(obri);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity update(ObrigacaoResponseDTO data, UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (data.idCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Obrigacao obrigacoes = repository.findObrigacaoByIdAndCliente(data.id(), user.getIdCliente());
        BeanUtils.copyProperties(data, obrigacoes);
        repository.save(obrigacoes);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity delete(ObrigacaoResponseDTO data, UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Obrigacao obrigacoes = repository.findObrigacaoByIdAndCliente(data.id(), user.getIdCliente());

        if (obrigacoes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(obrigacoes);
        return ResponseEntity.ok().build();
    }
}
