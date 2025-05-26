package com.orderservice.app.feign;

import com.orderservice.app.entity.NotificationDetails;
import org.springframework.stereotype.Component;

@Component
public class NotificationClientFallback implements NotificationClient {
   @Override
    public String sendEmail(NotificationDetails notificationDetails) {
         return "Notification service is not available";
    }
}
