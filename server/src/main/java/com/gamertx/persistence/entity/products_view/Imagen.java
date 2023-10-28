package com.gamertx.persistence.entity.products_view;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Getter
@Setter
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private int id;
    @Column(name = "id_producto")
    private int idProducto;
    private String nombre;
    private String url;
    private String identificador;

    public Imagen(){

    }
    public Imagen(Integer idProducto,String nombre, String url, String identificador) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.url = url;
        this.identificador = identificador;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
