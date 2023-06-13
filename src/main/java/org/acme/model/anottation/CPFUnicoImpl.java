package org.acme.model.anottation;

import org.acme.model.entity.Usuario;
import org.acme.model.service.UsuarioService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFUnicoImpl implements ConstraintValidator<CPFUnico, String> {

    @Inject
    UsuarioService usuarioService;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {

        Usuario usuario = usuarioService.consultarUsuarioCpf(cpf);

        return usuario.getCpf() == null;
    }
}
