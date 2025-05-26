package com.notificationservice.controller;


import com.notificationservice.dto.NotificationDetails;
import com.notificationservice.service.NotificationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @Operation (summary = "Send email notification")
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody NotificationDetails details) {
        System.out.println(details);
        return notificationService.sendMail(details);
    }

    @Operation (summary = "Send SMS notification")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody String email, @RequestParam String token) {
        notificationService.registerUser(email, token);
        return ResponseEntity.ok("User registered successfully and email sent");
    }
}
