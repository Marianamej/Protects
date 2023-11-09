package com.gamertx.persistence.mapper;

import com.gamertx.domain.Item;
import com.gamertx.persistence.entity.products_view.DetallePedido;
import com.gamertx.persistence.entity.products_view.DetallePedidoFK;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T08:27:29-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item toItem(DetallePedido detallePedido) {
        if ( detallePedido == null ) {
            return null;
        }

        Item item = new Item();

        Integer idProducto = detallePedidoIdIdProducto( detallePedido );
        if ( idProducto != null ) {
            item.setItemId( idProducto );
        }
        if ( detallePedido.getCantidad() != null ) {
            item.setQuantity( detallePedido.getCantidad() );
        }
        item.setTotal( detallePedido.getTotal() );

        return item;
    }

    @Override
    public DetallePedido toDetallePedido(Item item) {
        if ( item == null ) {
            return null;
        }

        DetallePedido detallePedido = new DetallePedido();

        detallePedido.setId( itemToDetallePedidoFK( item ) );
        detallePedido.setCantidad( item.getQuantity() );
        detallePedido.setTotal( item.getTotal() );

        return detallePedido;
    }

    private Integer detallePedidoIdIdProducto(DetallePedido detallePedido) {
        if ( detallePedido == null ) {
            return null;
        }
        DetallePedidoFK id = detallePedido.getId();
        if ( id == null ) {
            return null;
        }
        Integer idProducto = id.getIdProducto();
        if ( idProducto == null ) {
            return null;
        }
        return idProducto;
    }

    protected DetallePedidoFK itemToDetallePedidoFK(Item item) {
        if ( item == null ) {
            return null;
        }

        DetallePedidoFK detallePedidoFK = new DetallePedidoFK();

        detallePedidoFK.setIdProducto( item.getItemId() );

        return detallePedidoFK;
    }
}
