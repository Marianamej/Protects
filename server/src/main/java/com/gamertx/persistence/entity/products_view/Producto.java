package com.gamertx.persistence.entity.products_view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "Productos")
public class Producto {
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "id_marca")
    private Integer idMarca;

    @Column(name = "id_oferta")
    private Integer idOferta;

    private String nombre;

    private Short stock;
    private String descripcion;
    private BigDecimal precio;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Short valoracion;
    private String color;

    //Relacion Categoria con productos
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    //Relacion Marca con productos
    @ManyToOne
    @JoinColumn(name = "id_marca", insertable = false, updatable = false)
    private Marca marca;

    //Relacion Imagenes con producto
    @OneToMany(mappedBy = "producto",fetch=FetchType.EAGER)
    private List<Imagen> imagenes;

    @OneToMany(mappedBy = "producto",fetch=FetchType.EAGER)
    private List<Especificacion> especificacions;
}
