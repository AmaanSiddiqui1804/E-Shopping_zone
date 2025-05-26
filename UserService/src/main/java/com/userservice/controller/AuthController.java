package com.userservice.controller;

import com.userservice.dto.JwtRequest;
import com.userservice.dto.JwtResponse;
import com.userservice.dto.PasswordDTO;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "User Login", description = "Authenticate a user and return a JWT token.")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        return userService.login(request);
    }

    @Operation(summary = "Register a new user", description = "Create a new user account in the system.")
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update user password", description = "Update the password of an existing user by their ID.")
    @PutMapping("/updatePassword/{userId}")
    public ResponseEntity<String> updatePassword(@PathVariable int userId, @RequestBody PasswordDTO password) {
        return userService.updatePassword(userId, password.getPassword());
    }
}