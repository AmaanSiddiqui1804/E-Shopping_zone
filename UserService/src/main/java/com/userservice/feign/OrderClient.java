package com.userservice.feign;

import com.userservice.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDERSERVICE")
public interface OrderClient {
    @GetMapping("/orders/customer/{customerId}")
    List<OrderDTO> getOrdersByCustomerId(@PathVariable("customerId") int customerId);
}