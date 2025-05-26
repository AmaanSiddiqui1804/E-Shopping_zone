package com.productservice.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
 
import java.util.Set;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
public class ProductTest {
 
    private jakarta.validation.Validator validator;
 
    public ProductTest() {
        ValidatorFactory factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
 
    @Test
    public void testProductValidation() {
        // Create a valid Product instance
        Product product = new Product(1, "Electronics", "Laptop", "Gadgets", null, 999.99, "High performance laptop", 10.0);
 
        // Validate the product (should have no violations)
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(0, violations.size());
    }
 
    @Test
    public void testInvalidProduct() {
        // Create an invalid Product instance with missing productName
        Product product = new Product();
        product.setProductId(1);
        product.setProductType("Electronics");
        product.setCategory("Gadgets");
        product.setPrice(999.99);
        product.setDescription("High performance laptop");
        product.setDiscount(10.0);
 
        // Validate the product (should have violations)
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
 
        // Assert the number of violations
        assertEquals(1, violations.size());
 
        // Assert the specific message
        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("Product name cannot be blank", violation.getMessage());
    }
}

