package com.gamertx.domain;

import com.gamertx.persistence.entity.products_view.Imagen;
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
    private String name;
    private Short stockQuantity;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
    private Short rating;
    private String color;
    private List<String> urlsImages;
}
