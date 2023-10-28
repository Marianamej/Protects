package com.gamertx.persistence.mapper;

import com.gamertx.domain.Item;
import com.gamertx.persistence.entity.products_view.DetallePedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class,OrderMapper.class})
public interface ItemMapper {
    @Mappings({
            @Mapping(source = "id.idProducto",target = "itemId"),
            @Mapping(source = "cantidad",target = "quantity")
    })
    Item toItem(DetallePedido detallePedido);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "pedido", ignore = true),
            @Mapping(target = "id.idPedido", ignore = true),
    })
    DetallePedido toDetallePedido(Item item);
}
