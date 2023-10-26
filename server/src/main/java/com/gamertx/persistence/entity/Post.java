package com.gamertx.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @Column(name = "id_post")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    @Column(name = "email_usuario")
    private String emailUsuario;

    private String titulo;
    private String descripcion;
    private String contenido;

    @Column(name = "fecha_publicacion")
    private LocalTime fechaPublicacion;

    private Short likes;

    //Relacion entre Usuario y post
    @ManyToOne
    @JoinColumn(name = "email_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    //Relacion entre tematica y post
    @OneToMany(mappedBy = "post")
    private List<Tematica> tematicas;
}