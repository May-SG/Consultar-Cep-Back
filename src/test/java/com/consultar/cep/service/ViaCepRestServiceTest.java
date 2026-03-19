package com.consultar.cep.service;

import com.consultar.cep.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ViaCepRestServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ViaCepRestService viaCepRestService;

    private Endereco enderecoMock;

    @BeforeEach
    void setUp() {
        enderecoMock = new Endereco(
                "01001000", "Praça da Sé", "lado ímpar", "",
                "São Paulo", "SP", "São Paulo", "Sudeste",
                3550308, 1004, 11, 7107, false
        );
    }

    @Test
    @DisplayName("Deve retornar um endereço para um CEP válido")
    void buscarEnderecoPorCep_comCepValido_deveRetornarEndereco() {
        String cepValido = "01001000";
        String urlEsperada =
                String.format("https://viacep.com.br/ws/%s/json/", cepValido);

        when(restTemplate.getForEntity(urlEsperada, Endereco.class))
                .thenReturn(new ResponseEntity<>(enderecoMock, HttpStatus.OK));

        Endereco resultado = viaCepRestService.buscarEnderecoPorCep(cepValido);

        verify(restTemplate, times(1))
                .getForEntity(urlEsperada, Endereco.class);
        assertNotNull(resultado);
        assertEquals(cepValido, resultado.getCep());
        assertEquals("São Paulo", resultado.getLocalidade());
    }

    @Test
    @DisplayName("Deve lançar RuntimeException para um CEP não encontrado")
    void buscarEnderecoPorCep_comCepInvalido_deveLancarRuntimeException() {
        String cepInvaldo = "99999999";
        String urlEsperada =
                String.format("https://viacep.com.br/ws/%s/json/", cepInvaldo);

        when(restTemplate.getForEntity(urlEsperada, Endereco.class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            viaCepRestService.buscarEnderecoPorCep(cepInvaldo);
        });

        assertEquals("CEP não encontrado ou erro na consulta",
                exception.getMessage());
        verify(restTemplate, times(1))
                .getForEntity(urlEsperada, Endereco.class);
    }

}
