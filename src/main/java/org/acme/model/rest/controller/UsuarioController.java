package org.acme.model.rest.controller;

import org.acme.model.entity.Usuario;
import org.acme.model.rest.dto.UsuarioDTO;
import org.acme.model.rest.mapper.UsuarioMapper;
import org.acme.model.service.UsuarioServiceImpl;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")// Define o caminho da API rest
@Consumes(MediaType.APPLICATION_JSON)// informamos que será consumido o JSON na aplicação.
@Produces(MediaType.APPLICATION_JSON)// a API retornará JSON nas respostas.
public class UsuarioController {

    @Inject
    private UsuarioServiceImpl service;

    @Inject
    private UsuarioMapper mapper;

    @POST
    @Operation(summary = "Cria um Usuario")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UsuarioDTO.class))),

    })
    public Response salvarUser(@Valid UsuarioDTO dto) {

        Usuario usuario = service.salvarUser(dto);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(mapper.ConverterUsuarioDTO(usuario))
                .build();
    }

    @GET
    public Response listarTodos() {

        List<UsuarioDTO> listaUsuariosDTO = mapper.ConverterListaUsuarioDTO(service.listarTodos());

        if(listaUsuariosDTO.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
            return Response.ok(listaUsuariosDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletarUsuario(@PathParam("id") Long id) {
        if(service.deletarUsuario(id)){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}")
    public Response atualizarUsuario(@PathParam("id") Long id, @Valid UsuarioDTO dto) {
        Usuario usuario = service.atualizarUsuario(id, dto);



        return Response.ok(mapper.ConverterUsuarioDTO(usuario)).build();
    }
}