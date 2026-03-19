package com.consultar.cep.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {

    @Pattern(regexp = "^[0-9]{8}&",
            message = "CEP inválido.")
    private String cep;

    private String logradouro;
    private String complemento;
    private String unidade;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private int ibge;
    private int gia;
    private int ddd;
    private int siafi;
    private boolean erro;

}
