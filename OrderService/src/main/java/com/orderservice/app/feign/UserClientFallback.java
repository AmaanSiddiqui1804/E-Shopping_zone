package com.orderservice.app.feign;

import com.orderservice.app.dto.AddressDto;
import com.orderservice.app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        throw new RuntimeException("User service is not available");
    }

    @Override
    public AddressDto getUserAddress(int userId) {
        return null;
    }
}
