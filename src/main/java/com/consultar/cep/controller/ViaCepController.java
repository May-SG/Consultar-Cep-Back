package com.consultar.cep.controller;

import com.consultar.cep.model.Endereco;
import com.consultar.cep.service.ViaCepRestService;
import com.consultar.cep.service.ViaCepWebClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/rest/cep/{cep}")
    public ResponseEntity<Endereco> consultarCepRest(@Valid @PathVariable String cep) {
        return new ResponseEntity<>(restService.buscarEnderecoPorCep(cep),
                HttpStatus.OK);
    }

    @GetMapping("/client/cep/{cep}")
    public Mono<Endereco> consultarCepClient(@Valid @PathVariable String cep) {
        return clientService.buscarEnderecoPorCep(cep);
    }

}
