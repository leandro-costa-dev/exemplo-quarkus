package org.acme.model.exception.mapper;

import org.acme.model.rest.dto.ApiErrorsDTO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {

        List<String> listaErros = new ArrayList<>();
        for (ConstraintViolation<?> exception: e.getConstraintViolations()
             ) {
            listaErros.add(exception.getMessage());
        }

        ApiErrorsDTO apiErrorsDTO = new ApiErrorsDTO();
        apiErrorsDTO.setErros(listaErros);

        return Response.status(Response.Status.BAD_REQUEST).entity(apiErrorsDTO).build();
    }

}
