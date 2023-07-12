package org.acme.model.rest.controller;

import org.acme.model.rest.client.CepClient;
import org.acme.model.rest.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cep")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CepController {

    @Inject
    @RestClient
    private CepClient cepClient;

    @GET
    @Path("{cep}")
    public Response consultarCep(@PathParam("cep") String cep){
        EnderecoDTO enderecoDTO = cepClient.getEndereco(cep);
        return Response.ok(enderecoDTO).build();
    }
}
