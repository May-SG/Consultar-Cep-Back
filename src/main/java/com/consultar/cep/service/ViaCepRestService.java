package com.consultar.cep.service;

import com.consultar.cep.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepRestService {

    private static final String VIACEP_URL =
            "https://viacep.com.br/ws/%s/json/";

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("enderecos")
    public Endereco buscarEnderecoPorCep(String cep) {
        String url = String.format(VIACEP_URL, cep);
        ResponseEntity<Endereco> response =
                restTemplate.getForEntity(
                        url,
                        Endereco.class
                );
        if (response.getStatusCode() == HttpStatus.OK &&
        response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException(
                    "CEP não encontrado ou erro na consulta");
        }
    }

}
