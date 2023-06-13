package org.acme.model.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.model.anottation.CPFUnico;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Campo nome não pode ser branco!")
    private String nome ;

    @CPFUnico
    @CPF(message = "Informe um CPF válido!")
    @NotBlank(message = "Campo CPF deve ser obrigatório!!")
    private String cpf ;

    @NotNull(message = "Campo idade com informação obrigatoria!")
    private Integer idade;

}
