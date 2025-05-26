package com.cartservice.app.service;

import com.cartservice.app.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    ResponseEntity<Cart> getOrCreateCart(int customerId, String token);

    ResponseEntity<Cart> addItem(int itemId);

    void removeItem(int customerId, int itemId);

    ResponseEntity<Cart> getcartById(int cartId);

    ResponseEntity<Cart> updateCart(int cartId, Cart cart);

    ResponseEntity<List<Cart>> getallcarts();

    ResponseEntity<Cart> addCart(Cart cart);

    ResponseEntity<String> deleteCart(int cartId);

    ResponseEntity<List<Cart>> getAllByCustomerId(int customerId);
}