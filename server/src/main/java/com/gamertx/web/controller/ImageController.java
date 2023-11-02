package com.gamertx.web.controller;

import com.gamertx.domain.dto.Mensaje;
import com.gamertx.domain.service.CloudinaryService;
import com.gamertx.domain.service.ImageService;
import com.gamertx.persistence.entity.products_view.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;
    @GetMapping("/all")
    public ResponseEntity<List<Imagen>> allImages(){
        List<Imagen> list = imageService.allImages();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
//        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
//
//        if (bi == null){
//            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
//        }

        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen =
                new Imagen(
                        1,
                        (String) result.get("original_filename"),
                        (String) result.get("url"),
                        (String) result.get("public_id"));
        imageService.save(imagen);
        return new ResponseEntity(new Mensaje("Imagen cargada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException {
        if (!imageService.exist(id))
            return new ResponseEntity(new Mensaje("No existe"),HttpStatus.NOT_FOUND);
        Imagen imagen = imageService.getImage(id).get();
        Map result = cloudinaryService.delete(imagen.getIdentificador());
        imageService.delete(id);
        return new ResponseEntity(new Mensaje("Imagen Eliminada"), HttpStatus.OK);
    }
}
