package com.orderservice.app.feign;

import com.orderservice.app.entity.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CARTSERVICE")
public interface CartClient {

    @GetMapping("/carts/getCart/{cartId}")
    Cart getCartById(@PathVariable("cartId") int cartId);

    @DeleteMapping("/carts/deleteCart/{cartId}")
    void deleteCartById(@PathVariable("cartId") int cartId);
}