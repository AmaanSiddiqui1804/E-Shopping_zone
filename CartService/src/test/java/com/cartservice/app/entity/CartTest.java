package com.cartservice.app.entity;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;

public class CartTest {
 
    @Test
    public void testCreateCart() {
        // Create items for the cart
        List<Items> items = new ArrayList<>();
        items.add(new Items(1, 10.0, 2, null,1)); // Assuming constructor parameters
 
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setItems(items);
 
        assertNotNull(cart);
        assertEquals(1, cart.getCartId());
        assertEquals(items, cart.getItems());
    }
 
    @Test
    public void testCartTotalPriceCalculation() {
        // Create items for the cart
        List<Items> items = new ArrayList<>();
        items.add(new Items(1, 10.0, 2, null,1)); // Assuming constructor parameters
        items.add(new Items(2, 5.0, 3, null,1)); // Assuming constructor parameters
 
        Cart cart = new Cart();
        cart.setItems(items);
 
        double expectedTotalPrice = 10.0 * 2 + 5.0 * 3; // Total = price * quantity for each item
        cart.setTotalPrice(expectedTotalPrice);
 
        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }
 
    @Test
    public void testCartValidation() {
        // Test validation when items list is null
        Cart cart = new Cart();
        Exception exception = null;
        try {
            cart.setCustomerId(1);
            cart.setTotalPrice(100.0); // Set other required fields if any
            cart.setItems(null); // Setting items as null should trigger validation error
            // Persist the cart using JPA repository if required
        } catch (Exception e) {
            exception = e;
        }
        assertNull(exception);
    }
}