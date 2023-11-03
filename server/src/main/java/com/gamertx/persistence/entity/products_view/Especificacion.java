package com.gamertx.persistence.entity.products_view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "especificaciones")
public class Especificacion {
    @JsonIgnore
    @EmbeddedId
    private EspecificacionFK id;


    @ManyToOne
    @JoinColumn(name = "id_caracteristica", insertable = false, updatable = false)
    private Caracteristica caracteristica;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
