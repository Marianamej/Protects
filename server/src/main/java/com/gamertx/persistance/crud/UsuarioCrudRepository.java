package com.gamertx.persistance.crud;

import com.gamertx.persistance.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
//Este archivo es para las realizar consultas Crud en la base de datos mediante Query Methods que son los encargados
//de ir y traer la informacion y almacenarla para luego interactuar con los repositorios
public interface UsuarioCrudRepository extends CrudRepository <Usuario,String> {

    Usuario findByEmail(String email); //Consulta para hallar a un usuario buscando en la columna email
    List<Usuario> findByIsAdmin(Boolean isAdmin); //Consulta para hallar la lista de usuarios en la columna is_admin
}
