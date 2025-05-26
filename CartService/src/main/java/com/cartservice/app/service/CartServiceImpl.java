package com.cartservice.app.service;

import com.cartservice.app.entity.Cart;
import com.cartservice.app.entity.Items;
import com.cartservice.app.entity.Product;
import com.cartservice.app.entity.User;
import com.cartservice.app.exception.CartServiceException;
import com.cartservice.app.feign.ProductClient;
import com.cartservice.app.feign.UserClient;
import com.cartservice.app.repository.CartRepository;
import com.cartservice.app.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Override
    public ResponseEntity<Cart> getOrCreateCart(int customerId, String token) {
        // Extract email from JWT token
        String email = JwtUtil.extractEmail(token);

        // Call UserService to get user details
        User user = userClient.getUserByEmail(email);
        if (user == null || user.getUserId() != customerId) {
            throw new CartServiceException("Unauthorized access");
        }

        // Proceed with the existing logic
        Cart cart = cartRepository.findByCustomerId(customerId)
                .stream().findFirst()
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomerId(customerId);
                    return cartRepository.save(newCart);
                });
        return ResponseEntity.ok(cart);
    }

    @Override
    public ResponseEntity<Cart> addItem(int itemId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email == null) {
            throw new CartServiceException("Could not extract email from token");
        }

        // 2. Call user-service to get user details
        User user = userClient.getUserByEmail(email);
        if (user == null) {
            throw new CartServiceException("User not found with email: " + email);
        }

        int customerId = user.getUserId();
        // Check if a cart exists for the given customer ID, if not, create one
        Cart cart = cartRepository.findByCustomerId(customerId)
                .stream().findFirst()
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomerId(customerId);
                    return cartRepository.save(newCart);
                });

        // Fetch product details using the ProductClient
        Product product = productClient.getProductById(itemId);
        if (product == null) {
            throw new CartServiceException("Product not found with ID " + itemId);
        }

        // Create a new item and set its details from the product
        Items newItem = new Items();
        newItem.setCart(cart);
        newItem.setProductId(product.getProductId());
        newItem.setPrice(product.getPrice());
        newItem.setItemName(product.getProductName());
        newItem.setCategory(product.getCategory());
        newItem.setImage(product.getImage());
        newItem.setDescription(product.getDescription());
        newItem.setDiscount(product.getDiscount());
        newItem.setQuantity(1); // Default quantity to 1

        // Add the item to the cart
        cart.getItems().add(newItem);

        // Update the total price of the cart
        cart.setTotalPrice(cartTotal(cart));

        // Save the updated cart
        cart.setCustomerId(user.getUserId());
        Cart updatedCart = cartRepository.save(cart);
        addCart(updatedCart);

        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }


    @Override
    public void removeItem(int customerId, int itemId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .stream().findFirst()
                .orElseThrow(() -> new CartServiceException("Cart not found for customer ID " + customerId));

        boolean removed = cart.getItems().removeIf(item -> item.getItemId() == itemId);
        if (!removed) {
            throw new CartServiceException("Item not found with ID " + itemId);
        }

        cart.setTotalPrice(cartTotal(cart));
        cartRepository.save(cart);
    }

    @Override
    public ResponseEntity<Cart> getcartById(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartServiceException("Cart not found with ID " + cartId));
        return ResponseEntity.ok(cart);
    }

    @Override
    public ResponseEntity<Cart> updateCart(int cartId, @Valid Cart cart) {
        Optional<Cart> existingCart = cartRepository.findById(cartId);
        if (existingCart.isEmpty()) {
            throw new CartServiceException("Cart Id not present");
        }

        // Set the cart reference for each item
        cart.getItems().forEach(item -> {
            item.setCart(cart); // Set the bidirectional relationship
            Product product = productClient.getProductById(item.getProductId());
            if (product != null) {
                item.setPrice(product.getPrice());
            }
        });

        cart.setCartId(cartId);
        cart.setTotalPrice(cartTotal(cart));
        return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Cart>> getallcarts() {
        if (cartRepository.findAll().isEmpty()) {
            throw new CartServiceException("No any data present");
        }
        return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> addCart(@Valid Cart cart) {
        cart.getItems().forEach(item -> {
            Product product = productClient.getProductById(item.getProductId());
            if (product != null) {
                item.setPrice(product.getPrice());
            }
        });
        cart.setTotalPrice(cartTotal(cart));
        return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCart(int cartId) {
        Optional<Cart> c = cartRepository.findById(cartId);
        if (c.isEmpty()) {
            throw new CartServiceException("Cart Id not present");
        }
        cartRepository.deleteById(cartId);
        return new ResponseEntity<>("Items deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Cart>> getAllByCustomerId(int customerId) {
        List<Cart> carts = cartRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Helper method to calculate the total price of the cart
    private double cartTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}