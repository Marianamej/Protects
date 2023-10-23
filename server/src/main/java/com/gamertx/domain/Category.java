package com.gamertx.domain;

import com.gamertx.persistance.entity.EnumContent;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private int categoryId;

    @Enumerated(EnumType.STRING)
    private EnumContent.nombresCategoria category;

    private String description;
    private Short totalProducts;
}
