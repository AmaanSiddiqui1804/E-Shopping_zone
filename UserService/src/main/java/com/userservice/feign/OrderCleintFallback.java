package com.userservice.feign;

import com.userservice.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderCleintFallback implements OrderClient{

    @Override
    public List<OrderDTO> getOrdersByCustomerId(int customerId) {
        return List.of();
    }
}
