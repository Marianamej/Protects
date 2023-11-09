package com.gamertx.domain;


import com.gamertx.persistence.entity.users_view.Comentario;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Product {

    private int productId;

    private int categoryId;

    private int brandId;

    private int offerId;

    @NotEmpty
    @Size(min = 3, message = "EL Nombre del producto deberia tener al menos 3 caracteres")
    private String name;

    private Short stockQuantity;

    @NotEmpty
    @Size(min = 30, message = "La descripcion deberia tener al menos 30 caracteristicas")
    private String description;

    @NotNull
    @DecimalMin(value = "15000",message = "EL precio debe ser mayor a 15000")
    private BigDecimal price;
    private LocalDate creationDate;
    private Short rating;
    private String color;
    private List<String> urlsImages;
    //private List<Comentario> comentarios;
    private Integer numeroComentarios;
}
