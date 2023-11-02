package com.gamertx.domain.repository;
import com.gamertx.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    List<Product> getAll();
    Optional<Product> getProduct(int productId);
    Optional<List<Product>> getByCategory(int categoryId);
    Product saveProduct(Product product);
    void deleteProduct(int productId);
    Product updateProduct(Product newProduct, int id);
    Optional<List<Product>> getScarseProducts(int quantity);
}
