package org.acme.model.rest.controller;

import org.acme.model.rest.client.FipeClient;
import org.acme.model.rest.dto.FipeDTO;
import org.acme.model.rest.dto.UsuarioDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fipe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FipeController {

    @Inject
    @RestClient
    private FipeClient fipeClient;

    @Inject
    Jsonb jsonb;

    @GET
    @Path("/{codigoFipe}")
    public Response ConsultarFipe(@PathParam("codigoFipe") String codigoFipe){
        List<FipeDTO> respostaJson = fipeClient.ObterFipeVeiculo(codigoFipe);
        //FipeDTO fipeDTO = jsonb.fromJson(respostaJson, FipeDTO.class);
        return Response.ok(respostaJson).build();
    }

    @GET
    public Response Teste(){
        String teste = "{\n" +
                "\t\"id\": 1,\n" +
                "\"nome\": \"Leandro\",\n" +
                "\"idade\": 36\n" +
                "}";

        UsuarioDTO usuarioDTO = jsonb.fromJson(teste, UsuarioDTO.class);
        return Response.ok(usuarioDTO).build();
    }
}
