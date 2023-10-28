package com.gamertx.domain.repository;
import com.gamertx.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product saveProduct(Product product);
    void deleteProduct(int productId);
    Product updateProduct(Product newProduct, int id);
}
