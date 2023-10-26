package com.gamertx.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Tematica {
    @EmbeddedId
    private TematicaPK idTematica;

    //Relacion Tematica con Post
    @ManyToOne
    @JoinColumn(name = "id_post",insertable = false,updatable = false)
    private Post post;

    //Relacion Tematica con label
    @ManyToOne
    @JoinColumn(name = "id_label",insertable = false,updatable = false)
    private Label label;
}
