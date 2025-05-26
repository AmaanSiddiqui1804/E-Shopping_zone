package com.orderservice.app.service;

import com.orderservice.app.dto.AddressDto;
import com.orderservice.app.entity.*;
import com.orderservice.app.exception.CartNotFoundException;
import com.orderservice.app.exception.OrderNotFoundException;
import com.orderservice.app.feign.CartClient;
import com.orderservice.app.feign.NotificationClient;
import com.orderservice.app.feign.UserClient;
import com.orderservice.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private NotificationDetails notificationDetails;


    @Override
    public Order placeOrder(Order order) {
        // Get the authenticated user's email
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user and cart details
        User user = userClient.getUserByEmail(email);
        Cart cart = cartClient.getCartById(order.getCartId());

        // Fetch the address as AddressDto
        AddressDto addressDto = userClient.getUserAddress(user.getUserId());

        if (cart == null) {
            throw new CartNotFoundException("Your Cart is Empty");
        }

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Calculate the total order amount
        double orderTotal = cart.getTotalPrice();
        order.setAmountPaid(orderTotal);
        order.setOrderStatus(Status.PLACED);
        order.setCustomerId(user.getUserId());
        order.setCartId(cart.getCartId());

        // Map AddressDto to Address entity if required
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());

        // Set the address on the order
        order.setAddress(List.of(address));

        // Prepare and send the confirmation email
        notificationDetails.setRecipient(email);
        notificationDetails.setSubject("Order Confirmation");
        notificationDetails.setMsgBody("Your Order has been placed successfully");
        notificationClient.sendEmail(notificationDetails);

        // Save and return the order
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No Orders found!!");
        }
        return orders;
    }

    @Override
    public List<Order> getOrderByCustomerId(int customerId) {
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        if (orderList.isEmpty()) {
            throw new OrderNotFoundException("You have not ordered yet!");
        }
        return orderList;
    }

    @Override
    public Order changeOrderStatus(Status orderStatus, int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID " + orderId));
        order.setOrderStatus(orderStatus);
        NotificationDetails notificationDetails = new NotificationDetails();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        notificationDetails.setRecipient(email);
        notificationDetails.setSubject("Order Status");
        notificationDetails.setMsgBody("Your Order Status is " + order.getOrderStatus());
        notificationClient.sendEmail(notificationDetails);

        return orderRepository.save(order);
    }

    @Override
    public String cancelOrder(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return "Successfully Deleted";
        } else {
            throw new OrderNotFoundException("Order not found with ID " + orderId);
        }
    }


    @Override
    public List<Order> getLatestOrdersWithTime(LocalDateTime time) {
        return orderRepository.findByOrderDateAfter(time);
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
}