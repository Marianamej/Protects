package com.gamertx.persistence.mapper;

import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.persistence.entity.users_view.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T11:17:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class PersonalInfoMapperImpl implements PersonalInfoMapper {

    @Override
    public PersonalInfo toPersonalInfo(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName( usuario.getNombre() );
        personalInfo.setLastName( usuario.getApellido() );
        personalInfo.setAge( usuario.getEdad() );
        personalInfo.setBirthdate( usuario.getFechaNacimiento() );
        personalInfo.setImgProfile( usuario.getImgPerfil() );
        personalInfo.setEmail( usuario.getEmail() );
        personalInfo.setPassword( usuario.getPassword() );
        personalInfo.setUsername( usuario.getUsername() );

        return personalInfo;
    }

    @Override
    public Usuario toUsuario(PersonalInfo personalInfo) {
        if ( personalInfo == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setContraseña( personalInfo.getPassword() );
        usuario.setNombre( personalInfo.getName() );
        usuario.setApellido( personalInfo.getLastName() );
        usuario.setEdad( personalInfo.getAge() );
        usuario.setFechaNacimiento( personalInfo.getBirthdate() );
        usuario.setImgPerfil( personalInfo.getImgProfile() );
        usuario.setEmail( personalInfo.getEmail() );
        usuario.setUsername( personalInfo.getUsername() );

        return usuario;
    }
}
