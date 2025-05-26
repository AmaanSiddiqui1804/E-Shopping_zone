package com.productservice.service;

import com.productservice.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProductService {

    ResponseEntity<Product> addProduct(Product product);

    ResponseEntity<String> deleteProductById(int productId);

    ResponseEntity<List<Product>> getAllProducts();

    ResponseEntity<Product> updateProductById(int productId, Product product);

    ResponseEntity<Product> getProductById(int productId);


}
