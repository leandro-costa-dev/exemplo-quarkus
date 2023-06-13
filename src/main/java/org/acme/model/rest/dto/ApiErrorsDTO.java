package org.acme.model.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiErrorsDTO {
    List<String> erros;

}
