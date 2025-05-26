package com.userservice.controller;

import com.userservice.dto.AddressDTO;
import com.userservice.dto.CartDTO;
import com.userservice.dto.OrderDTO;
import com.userservice.entity.Address;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Create a new user", description = "This endpoint creates a new user in the system.")
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user's details using their unique ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Get user by email", description = "Retrieve a user's details using their email address.")
    @GetMapping("/email/{emailId}")
    public User getUserByEmailId(@PathVariable("emailId") String emailId) {
        System.out.println("🔐 Received token for: " + SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getUserByEmailId(emailId);
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system.")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update user", description = "Update the details of an existing user by their ID.")
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @Operation(summary = "Delete user", description = "Delete a user from the system using their unique ID.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @Operation(summary = "Get carts by customer ID", description = "Retrieve all carts associated with a specific customer ID.")
    @GetMapping("/cart/{customerId}")
    public ResponseEntity<List<CartDTO>> getCartsByCustomerId(@PathVariable("customerId") int customerId) {
        return userService.getCart(customerId);
    }

    @Operation(summary = "Get orders by customer ID", description = "Retrieve all orders associated with a specific customer ID.")
    @GetMapping("/orders/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable("customerId") int customerId) {
        return userService.getOrders(customerId);
    }

    @Operation(summary = "Get address by user ID", description = "Retrieve the address associated with a specific user ID.")
    @GetMapping("/{userId}/address")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable("userId") int userId) {
        return userService.getAddressByUserId(userId);
    }
}