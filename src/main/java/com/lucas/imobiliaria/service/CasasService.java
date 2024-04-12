package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.cliente.ClienteResponseDTO;
import com.lucas.imobiliaria.model.domain.imoveis.Casas;
import com.lucas.imobiliaria.model.domain.imoveis.CasasRequestDTO;
import com.lucas.imobiliaria.model.domain.imoveis.CasasResponseDTO;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.imoveis.imagensImoveis.ImagensResponseDTO;
import com.lucas.imobiliaria.model.domain.repository.CasasRepository;
import com.lucas.imobiliaria.model.domain.repository.ImagensRepository;
import com.lucas.imobiliaria.model.domain.repository.UsuariosRepository;
import com.lucas.imobiliaria.model.domain.users.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CasasService {

    @Autowired
    private CasasRepository casasAluguel;

    @Autowired
    private ImagensRepository imgRepository;

    @Autowired
    private UserStateCache userStateCache;

    public ResponseEntity getCasaById(Long idCasa, Long idCliente) {

        Casas casaDto = casasAluguel.findCasaByIdAndCliente(idCasa, idCliente);
        CasasResponseDTO responseDTO = new CasasResponseDTO(casaDto);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity getAll() {
        List<CasasResponseDTO> lista = casasAluguel.findAll().stream().map(CasasResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @Transactional
    public ResponseEntity register(CasasRequestDTO data, UserDetails userDetails) {
        System.out.println("\n\n" + data);
        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        System.out.println(user.toString());

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }
        Casas casa = new Casas(data);
        casa.setIdCliente(user.getIdCliente());
        imgRepository.saveAll(data.imagensImoveis());
        casasAluguel.save(casa);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity delete(CasasResponseDTO data, UserDetails userDetails) {

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Casas casa = casasAluguel.findCasaByIdAndCliente(data.id(), user.getIdCliente());
        if (casa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(casa);
        casasAluguel.delete(casa);

        List<ImagensImoveis> imgs = casa.getImagensImoveis();
        imgRepository.deleteAll(imgs);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity update(CasasResponseDTO data, UserDetails userDetails) {

        Usuarios user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Casas casa = casasAluguel.findCasaByIdAndCliente(data.id(), user.getIdCliente());
        BeanUtils.copyProperties(data, casa);
        casasAluguel.save(casa);
        return ResponseEntity.ok().build();
    }
}
