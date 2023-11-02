package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.products_view.Imagen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImagenCrudRepository extends CrudRepository<Imagen,Integer> {
    List<Imagen> findByOrderById();
}
