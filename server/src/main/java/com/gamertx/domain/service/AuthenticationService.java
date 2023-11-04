package com.gamertx.domain.service;

import com.gamertx.domain.dto.AuthenticationRequest;
import com.gamertx.domain.dto.AuthenticationResponse;
import com.gamertx.persistence.UsuarioRepository;
import com.gamertx.persistence.entity.users_view.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),authRequest.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);
        Usuario usuario = usuarioRepository.findByUsername(authRequest.getEmail()).get();

        String jwt = jwtService.generateToken(usuario, generateExtraClains(usuario));
        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClains(Usuario usuario) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuario.getNombre());
        extraClaims.put("role", usuario.getRole().name());

        return extraClaims;
    }
}
