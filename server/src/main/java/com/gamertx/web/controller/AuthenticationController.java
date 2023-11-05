package com.gamertx.web.controller;

import com.gamertx.domain.dto.AuthenticationRequest;
import com.gamertx.domain.dto.AuthenticationResponse;
import com.gamertx.domain.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login
            (@RequestBody @Valid AuthenticationRequest authRequest){
        AuthenticationResponse jwtDTO = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDTO);
    }
}
