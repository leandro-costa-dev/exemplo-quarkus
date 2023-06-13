package org.acme.model.rest.mapper;

import org.acme.model.entity.Usuario;
import org.acme.model.rest.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioMapper {
    public UsuarioDTO ConverterUsuarioDTO (Usuario usuario){
        //Converter automaticamente para classe
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario ConverterDTOParaEntidade (UsuarioDTO usuarioDTO){
        //Converter automaticamente para classe
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    public List<UsuarioDTO> ConverterListaUsuarioDTO (List<Usuario> listaUsuario){

        return listaUsuario.stream().map(this::ConverterUsuarioDTO)
                .collect(Collectors.toList());
    }
}
