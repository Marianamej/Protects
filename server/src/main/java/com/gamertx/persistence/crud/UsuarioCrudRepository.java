package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.users_view.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Este archivo es para las realizar consultas Crud en la base de datos mediante Query Methods que son los encargados
//de ir y traer la informacion y almacenarla para luego interactuar con los repositorios
public interface UsuarioCrudRepository extends JpaRepository<Usuario,String> {

    Optional<Usuario> findByEmail(String username); //Consulta para hallar a un usuario buscando en la columna email
}
