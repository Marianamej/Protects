package com.gamertx.domain.service;

import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;
import com.gamertx.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String newAccount(User user){
        String response = "";

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (usuarioRepository.newAccount(user) != null) {
            response = "Usuario creado";
        } else {
            response = "No se pudo crear";
        }
        return response;
    }

    public PersonalInfo getPersonalInfo (String email){
        return usuarioRepository.getPersonalInfo(email);
    }

    public PersonalInfo updateInfo (String email, PersonalInfo personalInfo){
        personalInfo.setPassword(passwordEncoder.encode(personalInfo.getPassword()));
        return usuarioRepository.updateInfo(email,personalInfo);
    }
}
