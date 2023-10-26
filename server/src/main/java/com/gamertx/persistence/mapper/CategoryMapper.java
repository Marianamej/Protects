package com.gamertx.persistence.mapper;

import com.gamertx.domain.Category;
import com.gamertx.persistence.entity.products_view.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "nombre",target = "category"),
            @Mapping(source = "descripcion",target = "description"),
            @Mapping(source = "numeroProductos",target = "totalProducts"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "url", ignore = true)
    Categoria toCategoria(Category category);
}
