package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.users_view.Comentario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioCrudRepository extends CrudRepository<Comentario,Integer> {
    Optional<List<Comentario>> findAllByIdProducto(int id);
}
