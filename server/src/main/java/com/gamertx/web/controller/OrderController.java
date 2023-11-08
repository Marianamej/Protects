package com.gamertx.web.controller;

import com.gamertx.domain.Item;
import com.gamertx.domain.Order;
import com.gamertx.domain.Product;
import com.gamertx.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class OrderController {

    @Autowired
    private OrderService service;
    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @GetMapping()
    public ResponseEntity<List<Order>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping(value = "/{email}")
    public ResponseEntity<List<Order>> getByClient(@PathVariable String email){

        Optional<List<Order>> ordenes = service.getByClient(email);

        return service.getByClient(email)
                .map(orders -> new ResponseEntity<>(orders, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/items/{email}")
    public ResponseEntity<List<Product>> getItemsByClient(@PathVariable String email){
        return new ResponseEntity<>(service.getItemsByClient(email),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @PostMapping(value = "/save")
    public ResponseEntity<Order> save(@RequestBody Order order){
        return new ResponseEntity<>(service.save(order), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @PutMapping(value = "/add/{id}")
    public ResponseEntity<Order> save(@RequestBody Item item, @PathVariable int id){
        return new ResponseEntity<>(service.addItem(item,id), HttpStatus.OK);
    }
}
