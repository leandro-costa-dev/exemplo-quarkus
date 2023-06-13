package org.acme.model.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.model.entity.Usuario;
import org.acme.model.repository.UsuarioRespository;
import org.acme.model.rest.dto.UsuarioDTO;
import org.acme.model.rest.mapper.UsuarioMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRespository usuarioRespository;

    @Inject
    private UsuarioMapper usuarioMapper;

    @Override
    @Transactional
    public Usuario salvarUser(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());
        usuario.setCpf(dto.getCpf());

        usuarioRespository.persist(usuarioMapper.ConverterDTOParaEntidade(dto));
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        PanacheQuery<Usuario> lista = usuarioRespository.findAll();
        return lista.list();
    }

    @Override
    @Transactional
    public boolean deletarUsuario(Long id) {
        Usuario usuario = usuarioRespository.findById(id);
        if (usuario != null) {
            usuarioRespository.delete(usuario);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRespository.findByIdOrThrow(id);

            usuario.setIdade(dto.getIdade());
            usuario.setNome(dto.getNome());

            return usuario;
    }

    @Override
    public Usuario consultarUsuarioCpf(String cpf) {
        return usuarioRespository.findByCpf(cpf);
    }
}
