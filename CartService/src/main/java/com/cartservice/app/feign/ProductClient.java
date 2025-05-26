package com.cartservice.app.feign;

import com.cartservice.app.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTSERVICE")
public interface ProductClient {
    @GetMapping("/products/getProductById/{productId}")
    Product getProductById(@PathVariable("productId") int productId);

    @GetMapping("/products/getProductByName/{productName}")
    Product getProductByName(@PathVariable("productName") String productName);
}