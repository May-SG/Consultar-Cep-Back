package com.consultar.cep.controller;

import com.consultar.cep.model.Endereco;
import com.consultar.cep.service.ViaCepRestService;
import com.consultar.cep.service.ViaCepWebClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("Consultar")
public class ViaCepController {

    private final ViaCepRestService restService;
    private final ViaCepWebClientService clientService;

    public ViaCepController(ViaCepRestService restService,
                            ViaCepWebClientService clientService) {
        this.restService = restService;
        this.clientService = clientService;
    }

    @GetMapping("/Get")
    public String get() {
        return "request Get";
    }

    @GetMapping("/RestCep/{cep}")
    public Endereco consultarCepRest(@PathVariable String cep) {
        return restService.buscarEnderecoPorCep(cep);
    }

    @GetMapping("/ClientCep/{cep}")
    public Mono<Endereco> consultarCepClient(@PathVariable String cep) {
        return clientService.buscarEnderecoPorCep(cep);
    }

}
