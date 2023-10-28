package com.gamertx.web.controller;

import com.gamertx.domain.Order;
import com.gamertx.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;
    @GetMapping()
    public ResponseEntity<List<Order>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<Order>> getByClient(@PathVariable String email){
        return service.getByClient(email)
                .map(orders -> new ResponseEntity<>(orders, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Order> save(@RequestBody Order order){
        return new ResponseEntity<>(service.save(order), HttpStatus.OK);
    }
}
