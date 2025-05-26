package com.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NOTIFICATIONSERVICE")
public interface NotificationClient {

    @PostMapping("/notification/register")
    String sendRegistrationEmail(@RequestBody String emailId, @RequestParam String token);
}
