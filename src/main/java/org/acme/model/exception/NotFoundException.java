package org.acme.model.exception;

import javax.ws.rs.ext.Provider;

public class NotFoundException extends RuntimeException{
    public NotFoundException(
            String mensagemErro
    ) {
        super("Objeto não encontrado: " + mensagemErro);
    }
}
