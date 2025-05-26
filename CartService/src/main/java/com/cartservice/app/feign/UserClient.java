package com.cartservice.app.feign;

import com.cartservice.app.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable("userId") int userId);

    @GetMapping("/user/email/{emailId}")
    User getUserByEmail(@PathVariable("emailId") String emailId);
}