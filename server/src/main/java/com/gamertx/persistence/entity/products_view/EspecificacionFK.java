package com.gamertx.persistence.entity.products_view;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class EspecificacionFK implements Serializable {
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;
}
