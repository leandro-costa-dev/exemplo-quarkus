package org.acme.model.service;

import org.acme.model.entity.Usuario;
import org.acme.model.rest.dto.UsuarioDTO;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UsuarioService {
    Usuario salvarUser(UsuarioDTO dto);

    List<Usuario> listarTodos();

    List<Usuario> listarFiltros(UsuarioDTO usuarioDTO);

    boolean deletarUsuario(Long id);

    Usuario atualizarUsuario(Long id, UsuarioDTO dto);

    Usuario consultarUsuarioCpf(String cpf);

}
