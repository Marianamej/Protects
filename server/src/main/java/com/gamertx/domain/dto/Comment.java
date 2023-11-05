package com.gamertx.domain.dto;

import com.gamertx.persistence.entity.users_view.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    @NotEmpty(message = "El email no puede estar vacio o nulo")
    @Email
    private String email;

    @NotNull(message = "Debe estar relacionado con algun producto")
    private Integer productId;

    @NotEmpty(message = "No puedes publicar un comentario vacio")
    @Size(max = 300 , message = "El comentario no puede superar los 300 caracteres")
    private String text;
    private LocalDate date;
    private Short rating;
    private Short likes;
    private Usuario user;
}
