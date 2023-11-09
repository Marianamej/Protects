package com.gamertx.persistence.mapper;

import com.gamertx.domain.Item;
import com.gamertx.domain.Order;
import com.gamertx.persistence.entity.products_view.DetallePedido;
import com.gamertx.persistence.entity.products_view.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T08:27:29-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Order toOrder(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        Order order = new Order();

        if ( pedido.getIdPedido() != null ) {
            order.setOrderId( pedido.getIdPedido() );
        }
        order.setUserEmail( pedido.getEmailUsuario() );
        order.setPurchaseDate( pedido.getFechaCompra() );
        order.setDeliveryDate( pedido.getFechaEntrega() );
        order.setStatus( pedido.getEstado() );
        order.setItems( detallePedidoListToItemList( pedido.getProductos() ) );
        order.setTotal( pedido.getTotal() );

        return order;
    }

    @Override
    public List<Order> toOrders(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toOrder( pedido ) );
        }

        return list;
    }

    @Override
    public Pedido toPedido(Order order) {
        if ( order == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setIdPedido( order.getOrderId() );
        pedido.setEmailUsuario( order.getUserEmail() );
        pedido.setFechaCompra( order.getPurchaseDate() );
        pedido.setFechaEntrega( order.getDeliveryDate() );
        pedido.setEstado( order.getStatus() );
        pedido.setProductos( itemListToDetallePedidoList( order.getItems() ) );
        pedido.setTotal( order.getTotal() );

        return pedido;
    }

    protected List<Item> detallePedidoListToItemList(List<DetallePedido> list) {
        if ( list == null ) {
            return null;
        }

        List<Item> list1 = new ArrayList<Item>( list.size() );
        for ( DetallePedido detallePedido : list ) {
            list1.add( itemMapper.toItem( detallePedido ) );
        }

        return list1;
    }

    protected List<DetallePedido> itemListToDetallePedidoList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<DetallePedido> list1 = new ArrayList<DetallePedido>( list.size() );
        for ( Item item : list ) {
            list1.add( itemMapper.toDetallePedido( item ) );
        }

        return list1;
    }
}
