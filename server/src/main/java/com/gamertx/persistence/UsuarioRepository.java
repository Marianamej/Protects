package com.gamertx.persistence;

import com.gamertx.domain.User;
import com.gamertx.persistence.crud.UsuarioCrudRepository;
import com.gamertx.persistence.entity.users_view.Usuario;
import com.gamertx.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper mapper;

    /**
     * Este metodo es utilizado para traer todos los usuarios existentes en mi base de datos
     * @return
     */
    public List<Usuario> getAll(){
        return (List<Usuario>)  usuarioCrudRepository.findAll();
    }

    public Optional<Usuario> findByUsername (String username){
      return usuarioCrudRepository.findByEmail(username);
    };
}
