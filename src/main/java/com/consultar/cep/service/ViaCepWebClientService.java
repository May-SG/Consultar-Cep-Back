package com.consultar.cep.service;

import com.consultar.cep.model.Endereco;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepWebClientService {

    private static final String VIACEP_URL =
            "https://viacep.com.br/ws/";

    private final WebClient webClient;

    public ViaCepWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(VIACEP_URL)
                .build();
    }

    @Cacheable("enderecos")
    public Mono<Endereco> buscarEnderecoPorCep(String cep) {
        return  webClient
                .get()
                .uri("{cep}/json/", cep)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> Mono.error(new RuntimeException(
                                "CEP não encontrado"
                        ))
                ).bodyToMono(Endereco.class);
    }

}
