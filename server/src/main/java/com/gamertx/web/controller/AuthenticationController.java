package com.gamertx.web.controller;

import com.gamertx.domain.dto.AuthenticationRequest;
import com.gamertx.domain.dto.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login
            (@RequestBody @Valid AuthenticationRequest authRequest){
        return null;
    }
}
