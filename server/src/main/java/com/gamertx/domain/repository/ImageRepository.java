package com.gamertx.domain.repository;

import com.gamertx.persistence.entity.products_view.Imagen;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    List<Imagen> getAll();
    Imagen save(Imagen imagen);
    void delete(int imageId);
    Optional<Imagen> getImage(int imageId);
    Boolean exist(int imageId);
}
