package com.gamertx.web.controller;

import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {
    @Autowired
    private UserService service;
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping()
    public ResponseEntity<Boolean> check(){
        try {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping()
//  @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<String> register(@Valid @RequestBody User user){
        service.newAccount(user);
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping("/{email}")
    public ResponseEntity<PersonalInfo> getUser(@Valid @PathVariable String email){
        return new ResponseEntity<>(service.getPersonalInfo(email), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @PutMapping("/{email}")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(@PathVariable String email,@Valid @RequestBody PersonalInfo personalInfo){
        return new ResponseEntity<>(service.updateInfo(email,personalInfo), HttpStatus.CREATED);
    }
}
