package com.gamertx.persistence;

import com.gamertx.domain.dto.Specification;
import com.gamertx.domain.repository.SpecificationRepository;
import com.gamertx.persistence.crud.ProductoCrudRepository;
import com.gamertx.persistence.entity.products_view.Producto;
import com.gamertx.persistence.mapper.ProductMapper;
import com.gamertx.persistence.mapper.SpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EspecificacionRepository implements SpecificationRepository {
    @Autowired
    private ProductoCrudRepository crud;
    @Autowired
    private SpecificationMapper mapper;

    @Override
    public Optional<Specification> getAll(int id) {
        return crud.findById(id).map(producto1 -> mapper.toSpecification(producto1));
    }
}
