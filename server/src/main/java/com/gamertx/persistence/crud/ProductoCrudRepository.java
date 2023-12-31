package com.gamertx.persistence.crud;

import com.gamertx.persistence.entity.products_view.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends JpaRepository<Producto,Integer> {
    Page<Producto> findByIdCategoria(int idCategoria, Pageable pageable);
    Optional<List<Producto>> findByStockLessThan(int stock);

}
