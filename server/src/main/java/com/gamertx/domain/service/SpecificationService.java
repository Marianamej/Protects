package com.gamertx.domain.service;

import com.gamertx.persistence.EspecificacionRepository;
import com.gamertx.persistence.entity.products_view.Especificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationService {
    @Autowired
    private EspecificacionRepository repository;

    public Optional<List<Especificacion>> getAll(int idProducto){
        return repository.getAll(idProducto).map(specification ->
                specification.getSpecifications());
    }

}
