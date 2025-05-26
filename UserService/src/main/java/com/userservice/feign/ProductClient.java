package com.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient ( name = "PRODUCTSERVICE")
public interface ProductClient {
    @PostMapping("/product/updateStock")
    String updateProductStock(@RequestParam String productId, @RequestParam int quantity);

    @GetMapping("/product/details")
    String getProductDetails(@RequestParam String productId);

    @DeleteMapping("/product/delete")
    String deleteProduct(@RequestParam String productId);
}
