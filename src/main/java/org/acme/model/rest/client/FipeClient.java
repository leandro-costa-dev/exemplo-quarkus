package org.acme.model.rest.client;

import org.acme.model.rest.dto.FipeDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@ApplicationScoped
@RegisterRestClient
public interface FipeClient {

    @GET
    @Path("{codigoFipe}")
    List<FipeDTO> ObterFipeVeiculo(@PathParam("codigoFipe") String codigoFipe);
}
