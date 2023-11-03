package com.gamertx.domain.dto;

import com.gamertx.persistence.entity.products_view.Especificacion;
import com.gamertx.persistence.entity.users_view.Usuario;
import com.gamertx.persistence.mapper.CommentMapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Details {
    private List<Comment> comentarios;
    private List<String> especificaciones;

    public Details(List<Comment> comentarios, List<String> especificaciones) {
        this.comentarios = comentarios;
        this.especificaciones = especificaciones;
    }
}
