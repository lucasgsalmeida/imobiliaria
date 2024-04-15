package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.obrigacoes.Obrigacoes;
import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListObrigacoes;
import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListRequestDTO;
import com.lucas.imobiliaria.model.domain.obrigacoes.listObrigacoes.ListResponseDTO;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import com.lucas.imobiliaria.model.repository.ListRepository;
import com.lucas.imobiliaria.model.repository.ObrigacoesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListService {

    @Autowired
    private ListRepository repository;

    @Autowired
    private ObrigacoesRepository obrigacoesRepository;

    @Autowired
    private UserStateCache userStateCache;

    public ResponseEntity register(ListRequestDTO data, UserDetails userDetails) {

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Obrigacoes obri = obrigacoesRepository.getById(data.idObrigacao());

        if (obri.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ListObrigacoes listObrigacoes = new ListObrigacoes(data);
        listObrigacoes.setIdCliente(user.getIdCliente());
        repository.save(listObrigacoes);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAll() {
        List<ListResponseDTO> list = repository.findAll().stream()
                .map(obri -> new ListResponseDTO(
                        obri.getId(),
                        obrigacoesRepository.getById(obri.getIdObrigacao()),
                        obri.getIdCliente(),
                        obri.getData(),
                        obri.getStatus())
                ).toList();
        return ResponseEntity.ok(list);
    }


    public ResponseEntity getById(Long id, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());
        ListObrigacoes obri = repository.getById(id);

        Obrigacoes obrigacoes = obrigacoesRepository.getById(obri.getIdObrigacao());

        if (obrigacoes.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (obri.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ListResponseDTO responseDTO = new ListResponseDTO(
                obri.getId(),
                obrigacoes,
                obri.getIdCliente(),
                obri.getData(),
                obri.getStatus()
        );
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity update(ListResponseDTO data, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Obrigacoes obrigacoes = obrigacoesRepository.getById(data.obrigacoes().getId());

        if (obrigacoes.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ListObrigacoes obri = repository.getById(data.id());

        if (obri.getIdCliente() != user.getIdCliente() && obri.getIdCliente() != data.idCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BeanUtils.copyProperties(data, obri);
        repository.save(obri);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity delete(ListResponseDTO data, UserDetails userDetails) {
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Obrigacoes obrigacoes = obrigacoesRepository.getById(data.obrigacoes().getId());

        if (obrigacoes.getIdCliente() != user.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ListObrigacoes obri = repository.getById(data.id());

        if (obri.getIdCliente() != user.getIdCliente() && obri.getIdCliente() != data.idCliente()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        repository.delete(obri);
        return ResponseEntity.ok().build();
    }
}
