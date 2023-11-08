package com.gamertx.persistence;

import com.gamertx.domain.Item;
import com.gamertx.domain.Order;
import com.gamertx.domain.Product;
import com.gamertx.domain.repository.OrderRepository;
import com.gamertx.persistence.crud.PedidoCrudRepository;
import com.gamertx.persistence.crud.ProductoCrudRepository;
import com.gamertx.persistence.entity.products_view.DetallePedido;
import com.gamertx.persistence.entity.products_view.Pedido;
import com.gamertx.persistence.entity.products_view.Producto;
import com.gamertx.persistence.mapper.ItemMapper;
import com.gamertx.persistence.mapper.OrderMapper;
import com.gamertx.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class PedidoRespository implements OrderRepository {
    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Order> getAll() {
        return mapper.toOrders((List<Pedido>) pedidoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Order>> getByClient(String email) {
        return pedidoCrudRepository.findByEmailUsuario(email).map(
                pedidos -> mapper.toOrders(pedidos)
        );
    }

    @Override
    public List<Product> getItemsByClient(String email){
        List<Integer> idProductos = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Order> ord =  pedidoCrudRepository.findByEmailUsuario(email).map(
                pedidos -> mapper.toOrders(pedidos)).orElse(null);

        for (Order orden : ord){
            List<Item> items =  orden.getItems();
            for (Item item : items){
                idProductos.add(item.getItemId());
            }
        }

        for (int id : idProductos ) {
            Producto producto = productoCrudRepository.findById(id).orElse(null);
            products.add(productMapper.toProduct(producto));
        }
        return products;
    }

    @Override
    public Order addItem(Item item,Integer orderId) {
        DetallePedido detallePedido = itemMapper.toDetallePedido(item);

    return  pedidoCrudRepository.findById(orderId).map(
            pedido -> {
                    pedido.setIdPedido(pedido.getIdPedido());
                    pedido.setEmailUsuario(pedido.getEmailUsuario());
                    pedido.setFechaCompra(pedido.getFechaCompra());
                    pedido.setTotal(pedido.getTotal());
                    pedido.setEstado(pedido.getEstado());
                    List<DetallePedido> itemsDePedido = pedido.getProductos();
                    itemsDePedido.add(detallePedido);
                    pedido.setProductos(itemsDePedido);

                    pedido.getProductos().forEach(detallePedido1 -> {
                        Producto producto = productoCrudRepository.findById(detallePedido1.getId().getIdProducto()).orElse(null);
                        detallePedido1.setTotal(new BigDecimal(detallePedido1.getCantidad()).multiply(producto.getPrecio()));
                        System.out.println(detallePedido1.getTotal());
                        detallePedido1.setPedido(pedido);
                    });
                    return mapper.toOrder(pedidoCrudRepository.save(pedido));
            }).get();
    }

    @Override
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        pedido.getProductos().forEach(producto -> producto.setPedido(pedido));
        return mapper.toOrder(pedidoCrudRepository.save(pedido));
    }
}
