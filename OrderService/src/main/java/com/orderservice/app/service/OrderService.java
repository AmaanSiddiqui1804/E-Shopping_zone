package com.orderservice.app.service;

import com.orderservice.app.entity.Order;
import com.orderservice.app.entity.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    List<Order> getOrderByCustomerId(int customerId);

    Order placeOrder(Order order);

    Order changeOrderStatus(Status orderStatus, int orderId);

    String cancelOrder(int orderId);

    List<Order> getLatestOrdersWithTime(LocalDateTime time);

    Optional<Order> getOrderById(int orderId);
}
