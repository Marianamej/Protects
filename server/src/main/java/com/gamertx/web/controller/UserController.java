package com.gamertx.web.controller;

import com.gamertx.domain.User;
import com.gamertx.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserController {
    @Autowired
    private UserService service;

    @PreAuthorize("permitAll()")
    @PostMapping()
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<String> register(@Valid @RequestBody User user){
        service.newAccount(user);
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }
}
