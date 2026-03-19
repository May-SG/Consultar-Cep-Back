package com.consultar.cep.service;

import com.consultar.cep.exceptions.ResourceNotFoundException;
import com.consultar.cep.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViaCepRestService {

    private static final String VIACEP_URL =
            "https://viacep.com.br/ws/%s/json/";

    private final RestTemplate restTemplate;

    public ViaCepRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("enderecos")
    public Endereco buscarEnderecoPorCep(String cep) {
        String url = String.format(VIACEP_URL, cep);
        ResponseEntity<Endereco> response =
                restTemplate.getForEntity(
                        url,
                        Endereco.class
                );
        if (response.getStatusCode() == HttpStatus.OK
                && response.getBody() != null) {
            Endereco endereco = response.getBody();
            if (endereco.isErro()) {
                throw new ResourceNotFoundException("CEP " + cep + " não encontrado pelo ViaCEP.");
            }
            return endereco;
        } else {
            throw new ResourceNotFoundException("CEP não encontrado ou erro na consulta");
        }
    }

}
