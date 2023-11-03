package com.gamertx.persistence.mapper;

import com.gamertx.domain.dto.Specification;
import com.gamertx.persistence.entity.products_view.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SpecificationMapper {
    @Mappings({
            @Mapping(source = "idProducto",target = "idProduct"),
            @Mapping(source = "especificacions",target = "specifications")
    })
    Specification toSpecification(Producto producto);
}
