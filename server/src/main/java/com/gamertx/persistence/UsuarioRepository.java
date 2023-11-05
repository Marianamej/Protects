package com.gamertx.persistence;

import com.gamertx.domain.Product;
import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.domain.repository.UserRepository;
import com.gamertx.exceptions.ResourceAlreadyExist;
import com.gamertx.exceptions.ResourceNotFoundException;
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
        Usuario usuario = mapper.toUsuario(user);

        if (!usuarioCrudRepository.existsById(usuario.getEmail())) {
            usuario.setImgPerfil("https://unavatar.io/github/37t?fallback=https://source.boringavatars.com/marble/120/1337_user?colors=264653r,2a9d8f,e9c46a,f4a261,e76f51");
            usuarioCrudRepository.save(usuario);
            return mapper.toUser(usuario);
        } else {
            throw new ResourceAlreadyExist(usuario.getEmail(),"ya existe");
        }
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
