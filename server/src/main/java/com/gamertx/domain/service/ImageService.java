package com.gamertx.domain.service;

import com.gamertx.domain.repository.ImageRepository;
import com.gamertx.persistence.entity.products_view.Imagen;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Imagen> allImages(){
        return imageRepository.getAll();
    }

    public Optional<Imagen> getImage(int imageId){
        return imageRepository.getImage(imageId);
    }

    public Imagen save(Imagen imagen){
        return imageRepository.save(imagen);
    }

    public void delete(int id){
        imageRepository.delete(id);
    }

    public boolean exist(int imageId){
        return imageRepository.exist(imageId);
    }
}
