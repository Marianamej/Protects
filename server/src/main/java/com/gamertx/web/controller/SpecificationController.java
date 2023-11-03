package com.gamertx.web.controller;

import com.gamertx.domain.service.SpecificationService;
import com.gamertx.persistence.entity.products_view.Especificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/details")
public class SpecificationController {
    @Autowired
    private SpecificationService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<Especificacion>> getAll(@PathVariable int id){
        return service.getAll(id)
                .map(especificacions -> new ResponseEntity<>(especificacions,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
