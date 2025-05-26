package com.notificationservice.service;

import com.notificationservice.dto.NotificationDetails;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    public ResponseEntity<?> sendMail(NotificationDetails details);

    public ResponseEntity<?> registerUser(String email, String password);
}
