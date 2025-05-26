package com.userservice.feign;

import com.userservice.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CARTSERVICE")
public interface CartClient {
    @GetMapping("/carts/getByCustomer/{customerId}")
    List<CartDTO> getCartsByCustomerId(@PathVariable("customerId") int customerId);
}