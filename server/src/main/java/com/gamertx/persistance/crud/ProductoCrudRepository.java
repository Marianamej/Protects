package com.gamertx.persistance.crud;

import com.gamertx.persistance.entity.Producto;
import com.gamertx.persistance.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByStockLessThan(int stock);
}
