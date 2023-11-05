package com.gamertx.domain;

import com.gamertx.utilities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @NotEmpty(message = "Debes ingresar un email")
    @Email(message = "Debe ser un email valido")
    private String email;

    @NotEmpty(message = "Debes ingresar una contraseña")
    @Size(min = 12, message = "La contraseña debe contener minimo 12 caracteres")
    private String password;

    @NotEmpty(message = "Debes ingresar un email")
    @Size(min = 6,message = "EL nombre de usuario debe tener al menos 6 caracteres")
    private String username;

    @NotNull
    private Role role;
}
