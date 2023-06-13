package org.acme.model.exception.mapper;

import org.acme.model.exception.NotFoundException;
import org.acme.model.rest.dto.ApiErrorsDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {

        List<String> listaErros = new ArrayList<>();
        listaErros.add(e.getMessage());

        ApiErrorsDTO apiErrorsDTO = new ApiErrorsDTO();
        apiErrorsDTO.setErros(listaErros);

        return Response.status(Response.Status.NOT_FOUND).entity(apiErrorsDTO).build();
    }
}
