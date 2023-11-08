package com.gamertx.persistence.mapper;

import com.gamertx.domain.User;
import com.gamertx.persistence.entity.users_view.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "contraseña",target = "password")
    })
    User toUser(Usuario usuario);

    @InheritInverseConfiguration
    @Mapping(target = "nombre",ignore = true)
    @Mapping(target = "apellido",ignore = true)
    @Mapping(target = "edad",ignore = true)
    @Mapping(target = "fechaNacimiento",ignore = true)
    @Mapping(target = "imgPerfil",ignore = true)
    @Mapping(target = "posts",ignore = true)
    @Mapping(target = "pagosUsuario",ignore = true)
    @Mapping(target = "comentarios",ignore = true)
    Usuario toUsuario(User user);
}
