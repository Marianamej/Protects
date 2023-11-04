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

    /**
     * Este metodo es utilizado para encontrar a un usuario pasandole el correo de ese usuario
     * @param email
     * @return EL resultado hallado en las consultas de UsuarioCrudRepository
     */
    public Usuario getByEmail(String email){
        return usuarioCrudRepository.findByEmail(email);
    }

    /**
     * Este metodo es para traer una lista de usuarios que determina cuales son administradores y cuales no
     * @param isAdmin
     * @return EL resultado hallado en las consultas de UsuarioCrudRepository bajo la consulta findByIsAdmin
     */
    public List<Usuario> findAdmins(Boolean isAdmin){
        return usuarioCrudRepository.findByIsAdmin(isAdmin);
    }

    public Optional<User> findByUsername (String email){
      return Optional.ofNullable(mapper.toUser(usuarioCrudRepository.findByEmail(email)));
    };
}
