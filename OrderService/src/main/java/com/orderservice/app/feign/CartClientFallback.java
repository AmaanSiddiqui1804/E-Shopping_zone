package com.orderservice.app.feign;

import com.orderservice.app.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartClientFallback implements CartClient {
    @Override
    public Cart getCartById(int cartId) {
        return null;
    }

    @Override
    public void deleteCartById(int cartId) {

    }
}
