package com.gamertx.persistence.mapper;

import com.gamertx.domain.Order;
import com.gamertx.persistence.entity.products_view.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface OrderMapper {
    @Mappings({
            @Mapping(source = "idPedido",target = "orderId"),
            @Mapping(source = "emailUsuario",target = "userEmail"),
            @Mapping(source = "fechaCompra",target = "purchaseDate"),
            @Mapping(source = "fechaEntrega",target = "deliveryDate"),
            @Mapping(source = "estado",target = "status"),
            @Mapping(source = "productos",target = "items"),
    })
    Order toOrder(Pedido pedido);
    List<Order> toOrders(List<Pedido> pedidos);

    @InheritInverseConfiguration
    Pedido toPedido(Order order);
}
