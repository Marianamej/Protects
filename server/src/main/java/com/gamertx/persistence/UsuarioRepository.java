package com.gamertx.persistence;

import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.domain.repository.UserRepository;
import com.gamertx.persistence.crud.UsuarioCrudRepository;
import com.gamertx.persistence.entity.users_view.Usuario;
import com.gamertx.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UserMapper mapper;

    public Optional<Usuario> findByUsername (String email){
      return usuarioCrudRepository.findByEmail(email);
    };

    @Override
    public User newAccount(User user) {
        Usuario usuario = usuarioCrudRepository.save(mapper.toUsuario(user));
        return mapper.toUser(usuario);
    }

    @Override
    public PersonalInfo getPersonalInfo(String email) {
        return null;
    }

    @Override
    public PersonalInfo updateInfo(String email, PersonalInfo personalInfo) {
        return null;
    }


}
