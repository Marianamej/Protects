package com.gamertx.domain.repository;

import com.gamertx.persistence.entity.products_view.Especificacion;

import java.util.List;

public interface SpecificationRepository {

    List<Especificacion> getAll(int id);
}
