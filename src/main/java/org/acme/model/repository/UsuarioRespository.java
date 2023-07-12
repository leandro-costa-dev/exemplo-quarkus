package org.acme.model.repository;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import org.acme.model.entity.Usuario;
import org.acme.model.exception.NotFoundException;
import org.acme.model.rest.dto.UsuarioDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Usuario> consultaComFiltro(UsuarioDTO usuarioDTO){
        Map<String, Object> params = new HashMap<>();

        String query = "";

        if(usuarioDTO.getId() != null){
            query += " id =: parId ";
            params.put("parId", usuarioDTO.getId());
        }

        if(usuarioDTO.getNome() != null){
            if(usuarioDTO.getId() != null){
                query += " and upper(nome) like upper(concat('%', :parNome, '%'))";
            }else {
                query += " upper(nome) like upper(concat('%', :parNome, '%'))";
            }
            params.put("parNome", usuarioDTO.getNome());
        }

        return find(query, params).list();
    }
}
