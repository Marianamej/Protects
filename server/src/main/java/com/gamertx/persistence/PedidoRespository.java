package com.gamertx.persistence;

import com.gamertx.domain.Order;
import com.gamertx.domain.repository.OrderRepository;
import com.gamertx.persistence.crud.PedidoCrudRepository;
import com.gamertx.persistence.entity.products_view.Pedido;
import com.gamertx.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class PedidoRespository implements OrderRepository {
    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;
    @Autowired
    private OrderMapper mapper;

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
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        pedido.getProductos().forEach(producto -> producto.setPedido(pedido));
        return mapper.toOrder(pedidoCrudRepository.save(pedido));
    }
}
