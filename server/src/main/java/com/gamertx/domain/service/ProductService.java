package com.gamertx.domain.service;


import com.gamertx.domain.Product;
import com.gamertx.domain.dto.Response;
import com.gamertx.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Response getAll(int pageNumber, int size, String sortBy,String sortField){
        return productRepository.getAll(pageNumber,size,sortBy, sortField);
    }

    public Product getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Response getByCategory(int categoryId,int pageNumber, int size, String sortBy,String sortField){
        return productRepository.getByCategory(categoryId,pageNumber,size,sortBy, sortField);
    }

    public Product save(Product product){
        return productRepository.saveProduct(product);
    }

    public Product updateProduct(Product product, int id){
        return productRepository.updateProduct(product,id);
    }

    public boolean delete(int productId) {
        if (getProduct(productId) != null){
            productRepository.deleteProduct(productId);
            return true;
        }else {
            return false;
        }
//        return getProduct(productId).map(product -> {
//            productRepository.deleteProduct(productId);
//            return true;
//        }).orElse(false);
    }
}