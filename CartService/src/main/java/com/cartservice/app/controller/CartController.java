package com.cartservice.app.controller;

import com.cartservice.app.entity.Cart;
import com.cartservice.app.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@NoArgsConstructor
public class CartController {

    @Autowired
    CartService cartService;

    @Operation(summary = "Get all carts by customer ID")
    @GetMapping("/getByCustomer/{customerId}")
    public ResponseEntity<List<Cart>> getAllByCustomerId(@PathVariable("customerId") int customerId) {
        return cartService.getAllByCustomerId(customerId);
    }

    @Operation(summary = "Get a cart by ID")
    @GetMapping("getCart/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable("cartId") int cartId) {
        return cartService.getcartById(cartId);
    }

    @Operation(summary = "Update a cart")
    @PutMapping("updateCart/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable("cartId") int cartId, @RequestBody @Valid Cart cart) {
        return cartService.updateCart(cartId, cart);
    }

    @Operation(summary = "Get all carts")
    @GetMapping("/getAllCarts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return cartService.getallcarts();
    }

    @Operation(summary = "Delete a cart")
    @DeleteMapping("deleteCart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable("cartId") int cartId) {
        return cartService.deleteCart(cartId);
    }

    @Operation(summary = "Add an item to the customer's cart")
    @PostMapping("/addItem/itemId/{itemId}")
    public ResponseEntity<Cart> addItem(@Valid @PathVariable int itemId) {
        return ResponseEntity.ok(cartService.addItem(itemId).getBody());
    }

    @Operation(summary = "Remove an item from the cart")
    @DeleteMapping("/removeFromId/{customerId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable @Min(1) int customerId,
            @PathVariable @Min(1) int itemId) {
        cartService.removeItem(customerId, itemId);
        return ResponseEntity.noContent().build();
    }
}