package com.gamertx.domain.repository;

import com.gamertx.domain.Item;
import com.gamertx.domain.Order;
import com.gamertx.domain.Product;
import com.gamertx.persistence.entity.products_view.Pedido;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<List<Order>> getByClient(String email);
    Order save(Order order);
    List<Product> getItemsByClient(String email);
    Order addItem(Item item, String email);
}
