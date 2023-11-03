package com.gamertx.web.controller;

import com.gamertx.domain.dto.Details;
import com.gamertx.domain.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class DetailController {
    @Autowired
    private DetailService service;

    @GetMapping("/{id}")
    public ResponseEntity<Details> getAll(@PathVariable int id){
        return new ResponseEntity<>(service.getAll(id),HttpStatus.OK);
    }
}
