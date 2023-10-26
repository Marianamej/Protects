package com.gamertx.persistence.entity.products_view;

import com.gamertx.persistence.entity.EnumContent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Categorias")
@Getter
@Setter
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Enumerated(EnumType.STRING)
    private EnumContent.nombresCategoria nombre;

    private String descripcion;

    @Column(name = "numero_productos")
    private Short numeroProductos;

    private String url;

    //Relacion Categoria con productos
    @OneToMany(mappedBy = "categoria",fetch=FetchType.EAGER)
    private List<Producto> productos;
}
