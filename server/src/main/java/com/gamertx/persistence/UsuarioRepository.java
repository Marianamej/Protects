package com.gamertx.persistence;

import com.gamertx.persistence.crud.UsuarioCrudRepository;
import com.gamertx.persistence.entity.users_view.Usuario;

import java.util.List;

public class UsuarioRepository {
    private UsuarioCrudRepository usuarioCrudRepository;

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

}
