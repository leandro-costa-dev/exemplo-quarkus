package org.acme.model.repository;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import org.acme.model.entity.Usuario;
import org.acme.model.exception.NotFoundException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped //Cria uma instancia do Repositorio no contexto para ser Injetado.
public class UsuarioRespository implements PanacheRepository<Usuario> {

    public Usuario findByIdOrThrow(long id) {
        return find("id =:id",
                Parameters.with("id", id)).firstResultOptional()
                .orElseThrow(
                        () -> new NotFoundException("Usuário " + id + " não encontrado..." ));
    }

    public Usuario findByCpf(String cpf){
        return find("cpf =:cpf",
                Parameters.with("cpf", cpf)).firstResultOptional()
                .orElse(new Usuario());
    }
}
