package com.productservice.service;

import com.productservice.entity.Product;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Product> addProduct(@Valid Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProductById(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product id not present");
        }
        productRepository.delete(product.get());
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        if (productRepository.findAll().isEmpty()) {
            throw new ProductNotFoundException("No any data present");
        }
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProductById(int productId, @Valid Product product) {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isEmpty()) {
            throw new ProductNotFoundException("Product not found with ID " + productId);
        }
        product.setProductId(productId);
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(int productId) {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isEmpty()) {
            throw new ProductNotFoundException("Product Id not found: " + productId);
        }
        return new ResponseEntity<>(p.get(), HttpStatus.OK);
    }


}
