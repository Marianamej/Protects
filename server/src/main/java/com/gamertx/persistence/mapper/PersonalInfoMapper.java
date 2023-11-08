package com.gamertx.persistence.mapper;

import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.persistence.entity.users_view.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PersonalInfoMapper {
    @Mappings({
            @Mapping(source = "nombre", target = "name"),
//            @Mapping(source = "contraseña", target = "password"),
            @Mapping(source = "apellido", target = "lastName"),
            @Mapping(source = "edad", target = "age"),
            @Mapping(source = "fechaNacimiento", target = "birthdate"),
            @Mapping(source = "imgPerfil", target = "imgProfile")
    })
    PersonalInfo toPersonalInfo(Usuario usuario);
    @InheritInverseConfiguration
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "posts",ignore = true)
    @Mapping(target = "pagosUsuario",ignore = true)
    @Mapping(target = "comentarios",ignore = true)
    @Mapping(source = "password",target = "contraseña")
    Usuario toUsuario(PersonalInfo personalInfo);
}
