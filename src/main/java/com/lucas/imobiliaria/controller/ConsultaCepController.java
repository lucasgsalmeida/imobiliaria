package com.lucas.imobiliaria.controller;

import com.lucas.imobiliaria.service.CepService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cep")
public class ConsultaCepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public CepService consultarCep(@PathVariable String cep) throws UnirestException {
        return cepService.getCep(cep);
    }

}
