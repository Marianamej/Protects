package com.gamertx.persistence.mapper;

import com.gamertx.domain.Category;
import com.gamertx.persistence.entity.products_view.Categoria;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T08:27:29-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        Category category = new Category();

        if ( categoria.getIdCategoria() != null ) {
            category.setCategoryId( categoria.getIdCategoria() );
        }
        category.setCategory( categoria.getNombre() );
        category.setDescription( categoria.getDescripcion() );
        category.setTotalProducts( categoria.getNumeroProductos() );

        return category;
    }

    @Override
    public Categoria toCategoria(Category category) {
        if ( category == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setIdCategoria( category.getCategoryId() );
        categoria.setNombre( category.getCategory() );
        categoria.setDescripcion( category.getDescription() );
        categoria.setNumeroProductos( category.getTotalProducts() );

        return categoria;
    }
}
