package com.productservice.controller;

import com.productservice.entity.Product;
import com.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Get a product by ID")
    @GetMapping("getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") int productId) {
        return productService.getProductById(productId);
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId, @RequestBody @Valid Product product) {
        return productService.updateProductById(productId, product);
    }

    @Operation(summary = "Get all products")
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Add a new product")
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {
        return productService.deleteProductById(productId);
    }
}