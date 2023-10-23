package com.gamertx.persistance.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Marca {
    @Id
    @Column(name = "id_marca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    private String nombre;

    @ElementCollection
    private Set<String> tipo;

    //Relacion Marca con productos
    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;
}
