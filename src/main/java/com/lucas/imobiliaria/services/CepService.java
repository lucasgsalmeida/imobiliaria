package com.lucas.imobiliaria.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CepService {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public CepService getCep(String cep) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://viacep.com.br/ws/" + cep + "/json/")
                .asJson();

        JsonNode responseBody = response.getBody();
        String logradouro = responseBody.getObject().getString("logradouro");
        String bairro = responseBody.getObject().getString("bairro");
        String localidade = responseBody.getObject().getString("localidade");
        String uf = responseBody.getObject().getString("uf");


        return new CepService(logradouro, bairro, localidade, uf);
    }
}

