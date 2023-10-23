package com.gamertx.domain;

import com.gamertx.persistance.entity.Marca;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Product {
    private int productId;
    private int categoryId;
    private Marca brandId;
    private int modelId;
    private int offerId;
    private String name;
    private Short stockQuantity;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
    private Short rating;
    private Category category;
}
