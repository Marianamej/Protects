package com.gamertx.domain.dto;

import com.gamertx.persistence.entity.users_view.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private String email;
    private Integer productId;
    private String text;
    private LocalDate date;
    private Short rating;
    private Short likes;
    private Usuario user;
}
