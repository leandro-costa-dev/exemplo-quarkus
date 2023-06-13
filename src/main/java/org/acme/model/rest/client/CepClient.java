package org.acme.model.rest.client;


import org.acme.model.rest.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@ApplicationScoped
@RegisterRestClient
public interface CepClient {

    @GET
    @Path("{cep}/json")
    EnderecoDTO getEndereco(@PathParam("cep") String cep);
}
