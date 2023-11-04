package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.products_view.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends JpaRepository<Producto,Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByStockLessThan(int stock);
}
