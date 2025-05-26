package com.userservice.feign;

import org.springframework.stereotype.Component;

@Component
public class NotificationClientFallback implements NotificationClient {
    @Override
    public String sendRegistrationEmail(String to, String token) {
        // Handle the fallback logic
        return "Failed to send email. Please try again later.";
    }
}
