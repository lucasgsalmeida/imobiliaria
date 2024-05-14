package com.lucas.imobiliaria.service;

import com.lucas.imobiliaria.infra.UserStateCache;
import com.lucas.imobiliaria.model.domain.imovel.Imovel;
import com.lucas.imobiliaria.model.domain.imovel.ImovelRequestDTO;
import com.lucas.imobiliaria.model.domain.imovel.ImovelResponseDTO;
import com.lucas.imobiliaria.model.domain.imovel.imagensImoveis.ImagensImoveis;
import com.lucas.imobiliaria.model.domain.users.Usuario;
import com.lucas.imobiliaria.model.repository.ImovelRepository;
import com.lucas.imobiliaria.model.repository.ImagensRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelAluguel;

    @Autowired
    private ImagensRepository imgRepository;

    @Autowired
    private UserStateCache userStateCache;

    public ResponseEntity getCasaById(Long idCasa, Long idCliente) {
        Imovel casaDto = imovelAluguel.findCasaByIdAndCliente(idCasa, idCliente);
        ImovelResponseDTO responseDTO = new ImovelResponseDTO(casaDto);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity getAll(UserDetails userDetails) {

        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ImovelResponseDTO> lista = imovelAluguel.findCasaByCliente(user.getIdCliente());
        return ResponseEntity.ok(lista);
    }

    @Transactional
    public ResponseEntity register(ImovelRequestDTO data, UserDetails userDetails) {
        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }


        Imovel casa = new Imovel(data);
        casa.setIdCliente(user.getIdCliente());
        imgRepository.saveAll(data.imagensImoveis());
        imovelAluguel.save(casa);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity delete(ImovelResponseDTO data, UserDetails userDetails) {

        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Imovel casa = imovelAluguel.findCasaByIdAndCliente(data.id(), user.getIdCliente());
        if (casa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        imovelAluguel.delete(casa);

        List<ImagensImoveis> imgs = casa.getImagensImoveis();
        imgRepository.deleteAll(imgs);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity update(ImovelResponseDTO data, UserDetails userDetails) {

        Usuario user = userStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Imovel casa = imovelAluguel.findCasaByIdAndCliente(data.id(), user.getIdCliente());
        BeanUtils.copyProperties(data, casa);
        imovelAluguel.save(casa);
        return ResponseEntity.ok().build();
    }
}
