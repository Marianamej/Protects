package com.gamertx.persistence.entity.products_view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private int id;
    private String nombre;
    private String url;
    private String identificador;

    public Imagen(){

    }
    public Imagen(String nombre, String url, String identificador) {
        this.nombre = nombre;
        this.url = url;
        this.identificador = identificador;
    }
}
