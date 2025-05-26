package com.cartservice.app.feign;

import com.cartservice.app.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }
}
