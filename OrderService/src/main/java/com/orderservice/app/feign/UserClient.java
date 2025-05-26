package com.orderservice.app.feign;

import com.orderservice.app.dto.AddressDto;
import com.orderservice.app.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable("userId") int userId);

    @GetMapping("/user/email/{emailId}")
    User getUserByEmail(@PathVariable("emailId") String emailId);

    // Change this to return AddressDto instead of Address entity
    @GetMapping("/user/{userId}/address")
    AddressDto getUserAddress(@PathVariable("userId") int userId);
}
