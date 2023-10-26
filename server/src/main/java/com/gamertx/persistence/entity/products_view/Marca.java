package com.gamertx.persistence.entity.products_view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamertx.persistence.entity.products_view.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "marcas")
public class Marca {
    @Id
    @Column(name = "id_marca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    private String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tipo;

    //Relacion Marca con productos
    @OneToMany(mappedBy = "marca",fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Producto> productos;
}
