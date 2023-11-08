package com.gamertx.domain.dto;


import lombok.Getter;
import lombok.Setter;

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
