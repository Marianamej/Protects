package com.gamertx.domain.repository;

import com.gamertx.domain.User;
import com.gamertx.domain.dto.PersonalInfo;

public interface UserRepository {
    User newAccount (User user);     //Creacion de un nuevo Usuario
    PersonalInfo getPersonalInfo (String email);  //Obtener la informacion del usuario
    PersonalInfo updateInfo (String email, PersonalInfo personalInfo); //Actualizar informacion
}
