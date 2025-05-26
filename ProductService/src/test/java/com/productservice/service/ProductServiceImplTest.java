package com.productservice.service;




import com.productservice.entity.Product;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    public void setup() {
        // Initialize test data before each test method
        testProduct = new Product();
        testProduct.setProductId(1);
        testProduct.setProductName("Test Product");
        testProduct.setPrice(50.0);
        testProduct.setDescription("Test description");
    }

    @Test
    public void testAddProduct_ValidProduct_ReturnsSavedProduct() {
        // Mock repository save
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // Call service method
        ResponseEntity<Product> response = productService.addProduct(testProduct);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getProductName());
    }

    @Test
    public void testDeleteProductById_ExistingId_DeletesProduct() {
        // Mock repository response
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));

        // Call service method
        ResponseEntity<String> response = productService.deleteProductById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteProductById_NonExistingId_ThrowsException() {
        // Mock repository response
        when(productRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(999));
        assertEquals("Product id not present", exception.getMessage());
    }

    @Test
    public void testGetAllProducts_ReturnsListOfProducts() {
        // Mock repository response
        List<Product> products = new ArrayList<>();
        products.add(testProduct);
        when(productRepository.findAll()).thenReturn(products);

        // Call service method
        ResponseEntity<List<Product>> response = productService.getAllProducts();

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
    }

    @Test
    public void testGetAllProducts_EmptyList_ThrowsException() {
        // Mock repository response
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Call service method and verify exception
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.getAllProducts());
        assertEquals("No any data present", exception.getMessage());
    }

    @Test
    public void testUpdateProduct_ExistingId_ReturnsUpdatedProduct() {
        // Mock repository response
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // Modify the test product
        testProduct.setPrice(75.0);

        // Call service method
        ResponseEntity<Product> response = productService.updateProductById(1, testProduct);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(75.0, response.getBody().getPrice());
    }

    @Test
    public void testUpdateProduct_NonExistingId_ThrowsException() {
        // Mock repository response
        when(productRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.updateProductById(999, testProduct));
        assertEquals("Product not found with ID 999", exception.getMessage());
    }

    @Test
    public void testGetProductById_ExistingId_ReturnsProduct() {
        // Mock repository response
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));

        // Call service method
        ResponseEntity<Product> response = productService.getProductById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getProductName());
    }

    @Test
    public void testGetProductById_NonExistingId_ThrowsException() {
        // Mock repository response
        when(productRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.getProductById(999));
        assertEquals("Product Id not found: 999", exception.getMessage());
    }

}
