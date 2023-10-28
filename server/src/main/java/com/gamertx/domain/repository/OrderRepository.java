package com.gamertx.domain.repository;

import com.gamertx.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<List<Order>> getByClient(String email);
    Order save(Order order);
}
