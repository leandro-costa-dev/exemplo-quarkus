package org.acme.model.rest.controller;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.acme.model.rest.client.CepClient;
import org.acme.model.rest.dto.EnderecoDTO;
import org.acme.model.rest.dto.UsuarioDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(CepController.class)
class CepControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioControllerTest.class);

    @Inject
    @InjectMock
    @RestClient
    private CepClient cepClientMoc;

    @Test
    @DisplayName("Deve consultar um endereço pelo CEP")
    public void consultaEnderecoPeloCEP() {
        var resposta = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/85804060")
                .then()
                .extract().response();
        //
        assertEquals(200, resposta.statusCode());
    }

    @Test
    @DisplayName("Deve consultar um endereço pelo CEP (mocado)")
    public void consultaEnderecoPeloCEPMocado() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Rua teste");

        Mockito.when(cepClientMoc.getEndereco(any())).thenReturn(enderecoDTO);

        var resposta = given()
                .contentType(ContentType.JSON)
                .pathParam("cep","8580000")
                .when()
                .get("{cep}")
                .then()
                .extract().response();

        //
        String bodyResponse = resposta.getBody().asString();
        LOG.info("LOG: " + bodyResponse);

        assertEquals(200, resposta.statusCode());
        assertEquals("Rua teste", resposta.jsonPath().getString("logradouro"));
    }
}