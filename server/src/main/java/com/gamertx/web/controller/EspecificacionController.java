package com.gamertx.web.controller;

import com.gamertx.domain.service.SpecificationService;
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
public class EspecificacionController {
    @Autowired
    private SpecificationService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getAll(@PathVariable int id){
        return new ResponseEntity<>(service.getAll(id),HttpStatus.OK);
    }
}
