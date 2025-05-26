package com.cartservice.app.feign;

import com.cartservice.app.entity.User;
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
}
