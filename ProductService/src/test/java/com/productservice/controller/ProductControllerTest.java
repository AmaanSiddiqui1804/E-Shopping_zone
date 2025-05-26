package com.productservice.controller;

import com.productservice.entity.Product;
import com.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId(1);
        sampleProduct.setProductName ("Sample Product");
        sampleProduct.setPrice(10.0);
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(1)).thenReturn(ResponseEntity.of(Optional.of(sampleProduct)));

        ResponseEntity<Product> response = productController.getProductById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProduct, response.getBody());
    }

    @Test
    void testUpdateProduct() {
        when(productService.updateProductById(1, sampleProduct)).thenReturn(ResponseEntity.of(Optional.of(sampleProduct)));

        ResponseEntity<Product> response = productController.updateProduct(1, sampleProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProduct, response.getBody());
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(sampleProduct);
        when(productService.getAllProducts()).thenReturn(ResponseEntity.ok(productList));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testAddProduct() {
        when(productService.addProduct(any(Product.class))).thenReturn(ResponseEntity.ok(sampleProduct));

        ResponseEntity<Product> response = productController.addProduct(sampleProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProduct, response.getBody());
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProductById(1)).thenReturn(ResponseEntity.ok("Product deleted successfully"));

        ResponseEntity<String> response = productController.deleteProduct(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }
}
