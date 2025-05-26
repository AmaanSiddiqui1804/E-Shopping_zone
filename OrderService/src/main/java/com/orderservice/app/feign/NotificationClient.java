package com.orderservice.app.feign;

import com.orderservice.app.entity.NotificationDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATIONSERVICE")
public interface NotificationClient {

    @PostMapping("/notification/sendMail")
    String sendEmail(@RequestBody NotificationDetails notificationDetails);
}
