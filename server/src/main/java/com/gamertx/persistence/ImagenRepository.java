package com.gamertx.persistence;

import com.gamertx.domain.repository.ImageRepository;
import com.gamertx.persistence.crud.ImagenCrudRepository;
import com.gamertx.persistence.entity.products_view.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImagenRepository implements ImageRepository {
    @Autowired
    private ImagenCrudRepository imagenCrudRepository;
    @Override
    public List<Imagen> getAll() {
        return imagenCrudRepository.findByOrderById();
    }

    @Override
    public Imagen save(Imagen imagen) {
        imagenCrudRepository.save(imagen);
        return imagen;
    }

    @Override
    public void delete(int imageId) {
        imagenCrudRepository.deleteById(imageId);
    }

    @Override
    public Optional<Imagen> getImage(int imageId) {
        return imagenCrudRepository.findById(imageId);
    }

    @Override
    public Boolean exist(int imageId) {
        return imagenCrudRepository.existsById(imageId);
    }
}
