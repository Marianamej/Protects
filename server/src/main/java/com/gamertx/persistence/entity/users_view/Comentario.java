package com.gamertx.persistence.entity.users_view;

import com.gamertx.persistence.entity.products_view.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "comentarios")
public class Comentario {
    @Id
    @Column(name = "id_comentario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuarios_email",nullable = false)
    private String emailUsuario;

    @Column(name = "id_producto",nullable = false)
    private Integer idProducto;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private LocalDate fecha;

    private Short calificacion;

    private Short likes;

    @ManyToOne
    @JoinColumn(name = "email_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
