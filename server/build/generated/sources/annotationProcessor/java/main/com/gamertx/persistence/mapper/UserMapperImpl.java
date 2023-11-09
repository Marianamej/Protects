package com.gamertx.persistence.mapper;

import com.gamertx.domain.User;
import com.gamertx.persistence.entity.users_view.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T11:17:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( usuario.getContraseña() );
        user.setEmail( usuario.getEmail() );
        user.setUsername( usuario.getUsername() );
        user.setRole( usuario.getRole() );

        return user;
    }

    @Override
    public Usuario toUsuario(User user) {
        if ( user == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setContraseña( user.getPassword() );
        usuario.setEmail( user.getEmail() );
        usuario.setUsername( user.getUsername() );
        usuario.setRole( user.getRole() );

        return usuario;
    }
}
