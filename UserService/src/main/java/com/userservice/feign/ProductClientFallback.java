package com.userservice.feign;

import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String getProductDetails(String productId) {
        // Handle the fallback logic
        return "Failed to retrieve product details. Please try again later.";
    }

    @Override
    public String updateProductStock(String productId, int quantity) {
        // Handle the fallback logic
        return "Failed to update product stock. Please try again later.";
    }

    @Override
    public String deleteProduct(String productId) {
        // Handle the fallback logic
        return "Failed to delete product. Please try again later.";
    }
}
