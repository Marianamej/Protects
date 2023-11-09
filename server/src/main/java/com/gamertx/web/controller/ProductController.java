package com.gamertx.web.controller;

import com.gamertx.domain.Product;
import com.gamertx.domain.dto.Response;
import com.gamertx.domain.service.ProductService;
import com.gamertx.utilities.AppConst;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PreAuthorize("permitAll()")
    @GetMapping()
    public ResponseEntity<Response> getAll(
            @RequestParam(value = "pageNo",defaultValue = AppConst.DEFAULT_NUMBER_PAGE,required = false) int pageNumber,
            @RequestParam(value = "size",defaultValue = AppConst.SIZE_NUMBER_PAGE, required = false) int size,
            @RequestParam(value = "sortBy",defaultValue = AppConst.DEFAULT_SORT,required = false) String sortBy,
            @RequestParam(value = "sortField",defaultValue = AppConst.DEFAULT_SORT_DIRECTION,required = false) String sortField)
    {
        return new ResponseEntity<>(productService.getAll(pageNumber,size,sortBy,sortField), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return new ResponseEntity<>(productService.getProduct(productId),HttpStatus.FOUND);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/{id}")
    public ResponseEntity<Response> getByCategory(
            @PathVariable("id") int categoryId,
            @RequestParam(value = "pageNo",defaultValue = AppConst.DEFAULT_NUMBER_PAGE,required = false) int pageNumber,
            @RequestParam(value = "size",defaultValue = AppConst.SIZE_NUMBER_PAGE, required = false) int size,
            @RequestParam(value = "sortBy",defaultValue = AppConst.DEFAULT_SORT,required = false) String sortBy,
            @RequestParam(value = "sortField",defaultValue = AppConst.DEFAULT_SORT_DIRECTION,required = false) String sortField)
    {

        Response products = productService.getByCategory(categoryId,pageNumber,size,sortBy,sortField);

        return products != null ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PostMapping()
    public ResponseEntity<Product> save(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PutMapping("/{}")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product, @PathVariable("id") int productId){
        productService.updateProduct(product,productId);
        return  new ResponseEntity<>("El producto ha sido actualizado",HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
        return productService.delete(productId) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
