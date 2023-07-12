package org.acme.model.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.exception.NotFoundException;
import org.acme.model.rest.dto.UsuarioDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(UsuarioController.class)
public class UsuarioControllerTest {

    //private static final String Path_NovoUsuario = "src/main/teste/mock-json/novo-usuario.json";
    private static final String Path_Json = "{\"nome\":\"Joao da Silva\", \"cpf\": \"43410267042\", \"idade\": 35}";
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioControllerTest.class);

    @Test
    @Order(1)
    @DisplayName("Deve retornar lista vazia com sucesso")
    public void ListaVaziaUsuarios() {
        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get()
                .then()
                .extract().response();

        assertEquals(204, resposta.statusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve Criar um usuário POST com sucesso")
    public void criarUsuarioTeste() {
        var userDTO = new UsuarioDTO();
        userDTO.setIdade(15);
        userDTO.setNome("Thiago junior");
        userDTO.setCpf("05817441950");

        var resposta = given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when()
                .post()
                .then()
                .extract().response();
        //
        assertEquals(201, resposta.statusCode());
        assertNotNull(resposta.jsonPath().getString("nome"));
        assertNotNull(resposta.jsonPath().getString("idade"));
    }

    @Test
    @Order(3)
    @DisplayName("Deve Criar usuário POST com sucesso")
    public void criarUsuarioTestExemplo() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        UsuarioDTO userDTO = mapper.readValue(Path_Json, UsuarioDTO.class);

        var resposta = given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when()
                .post()
                .then()
                .extract().response();

        String mensagemRetorno = resposta.getBody().asString();
        LOG.info("LOG: " + mensagemRetorno);

        assertEquals(201, resposta.statusCode());
        assertEquals("Joao da Silva", resposta.jsonPath().getString("nome"));
        assertEquals("43410267042", resposta.jsonPath().getString("cpf"));
        assertEquals("35", resposta.jsonPath().getString("idade"));
        assertNotNull(resposta.jsonPath().getString("nome"));
        assertNotNull(resposta.jsonPath().getString("idade"));
    }

    @Test
    @Order(4)
    @DisplayName("Deve retornar lista com um usuário com sucesso")
    public void ListarTodosUsuarios() {
        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get()
                .then()
                .body("size()", Matchers.is(2))
                .extract().response();

        assertEquals(200, resposta.statusCode());
    }

    @Test
    @Order(5)
    @DisplayName("Deve retornar lista de usuário com filtro")
    public void ListarUsuariosComFiltro() {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Thiago junior");

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(usuarioDTO)
                .when()
                .get("/filtro")
                .then()
                .extract().response();

        String mensagemRetorno = resposta.getBody().asString();
        LOG.info("LOG: " + mensagemRetorno);

        assertEquals(200, resposta.statusCode());
    }

    @Test
    @Order(6)
    @DisplayName("Não deve deletar um usuário com id inexistente")
    public void NaoDeletarUsuario() {
        var resposta = given()
                //.pathParam("id", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete("/100")
                .then()
                .extract().response();

        assertEquals(200, resposta.statusCode());
    }

    @Test
    @Order(7)
    @DisplayName("Deve atualizar um usuário com sucesso")
    public void AtualizarUsuario() {
        UsuarioDTO userDTO = new UsuarioDTO();
        userDTO.setIdade(10);
        userDTO.setNome("Leandro");
        userDTO.setCpf("58743048005");

        var resposta = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO)
                .when()
                .put("/1")
                .then()
                .extract().response();

        String mensagemRetorno = resposta.getBody().asString();
        LOG.info("LOG: " + mensagemRetorno);

        assertEquals(200, resposta.statusCode());
    }

    @Test()
    @Order(8)
    @DisplayName("Não deve atualizar um usuário inexistente")
    public void AtualizarUsuarioInexistente() {
        UsuarioDTO userDTO = new UsuarioDTO();
        userDTO.setIdade(10);
        userDTO.setNome("Leandro");
        userDTO.setCpf("05817441950");

        assertThrows(NotFoundException.class, () -> {
                    given()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userDTO)
                    .when()
                    .put("/9999999")
                    .then()
                    .extract().response();

                throw new NotFoundException("Ocorreu erro ao atualizar o usuario");
            });
        }

        @Test
        @Order(9)
        @DisplayName("Deve deletar um usuário com sucesso")
        public void DeletarUsuario () {
            var resposta = given()
                    .contentType(MediaType.APPLICATION_JSON)
                    .when()
                    .delete("/1")
                    .then()
                    .extract().response();

            assertEquals(204, resposta.statusCode());
        }
    }
