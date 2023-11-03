package com.gamertx.domain.dto;

import com.gamertx.persistence.entity.users_view.Usuario;
import com.gamertx.persistence.mapper.CommentMapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Details {
    private String text;
    private LocalDate date;
    private Short rating;
    private String email;
    private Integer productId;
    private Short likes;
    private Usuario user;

}
