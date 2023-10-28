package com.gamertx.domain.service;


import com.gamertx.domain.Product;
import com.gamertx.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.saveProduct(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.deleteProduct(productId);
            return true;
        }).orElse(false);
    }
}