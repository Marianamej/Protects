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
    @EmbeddedId
    private EspecificacionFK id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Caracteristica caracteristica;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
