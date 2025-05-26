package com.userservice.feign;

import com.userservice.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartClientFallback implements CartClient {

    @Override
    public List<CartDTO> getCartsByCustomerId(int customerId) {
        return List.of();
    }
}
