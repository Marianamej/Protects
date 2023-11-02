package com.gamertx.domain.service;

import com.gamertx.domain.Order;
import com.gamertx.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll(){
        return orderRepository.getAll();
    }
    public Optional<List<Order>> getByClient(String email){
        return orderRepository.getByClient(email);
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
