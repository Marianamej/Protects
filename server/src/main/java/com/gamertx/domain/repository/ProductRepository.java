package com.gamertx.domain.repository;
import com.gamertx.domain.Product;
import com.gamertx.domain.dto.Response;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    Response getAll(int pageNumber, int size, String sortBy, String sortField);
    Optional<Product> getProduct(int productId);
    Optional<List<Product>> getByCategory(int categoryId);
    Product saveProduct(Product product);
    void deleteProduct(int productId);
    Product updateProduct(Product newProduct, int id);
    Optional<List<Product>> getScarseProducts(int quantity);
}
