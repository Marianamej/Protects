package com.gamertx.persistence;

import com.gamertx.domain.repository.SpecificationRepository;
import com.gamertx.persistence.crud.ProductoCrudRepository;
import com.gamertx.persistence.entity.products_view.Especificacion;
import com.gamertx.persistence.entity.products_view.Producto;
import com.gamertx.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspecificacionRepository implements SpecificationRepository {
    @Autowired
    private ProductoCrudRepository crud;

    @Autowired
    private ProductMapper mapper;
    @Override
    public List<Especificacion> getAll(int id) {
        Producto producto = crud.findById(id).map(producto1 -> (Producto) producto1).orElse(null);
        return producto.getEspecificacions();
    }
}
