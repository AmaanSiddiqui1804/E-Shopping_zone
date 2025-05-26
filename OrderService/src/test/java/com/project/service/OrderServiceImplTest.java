package com.project.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.orderservice.app.entity.Cart;
import com.orderservice.app.entity.Items;
import com.orderservice.app.entity.Order;
import com.orderservice.app.entity.Status;
import com.orderservice.app.exception.CartNotFoundException;
import com.orderservice.app.exception.OrderNotFoundException;
import com.orderservice.app.repository.OrderRepository;
import com.orderservice.app.service.OrderServiceImpl;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testPlaceOrder_Success() {
//        // Mock Cart
//        Cart mockCart = new Cart();
//        mockCart.setTotalPrice(1000.0);
//        mockCart.setItems(Arrays.asList(new Items(1, 500.0,2,null,3)));
//
//        Order order = new Order();
//        order.setCartId(1);
//
//        when(restTemplate.getForObject("http://cartservice/carts/getCart/1", Cart.class)).thenReturn(mockCart);
//        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Order savedOrder = orderService.placeOrder(order);
//
//        assertEquals(1000.0, savedOrder.getAmountPaid());
//        assertEquals(Status.PLACED, savedOrder.getOrderStatus());
//        verify(restTemplate).delete("http://cartservice/carts/deleteCart/1");
//        verify(orderRepository).save(any(Order.class));
//    }

    @Test
    void testPlaceOrder_CartNotFound() {
        when(restTemplate.getForObject("http://cartservice/carts/getCart/1", Cart.class)).thenReturn(null);

        Order order = new Order();
        order.setCartId(1);

        assertThrows(CartNotFoundException.class, () -> orderService.placeOrder(order));
    }

    @Test
    void testGetAllOrders_NoOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(OrderNotFoundException.class, () -> orderService.getAllOrders());
    }

    @Test
    void testChangeOrderStatus_Success() {
        Order mockOrder = new Order();
        mockOrder.setOrderId(1);
        mockOrder.setOrderStatus(Status.PLACED);

        when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order updatedOrder = orderService.changeOrderStatus(Status.SHIPPED, 1);

        assertEquals(Status.SHIPPED, updatedOrder.getOrderStatus());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testCancelOrder_Success() {
        Order mockOrder = new Order();
        mockOrder.setOrderId(1);

        when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
        doNothing().when(orderRepository).delete(mockOrder);

        String result = orderService.cancelOrder(1);

        assertEquals("Successfully Deleted", result);
        verify(orderRepository).delete(mockOrder);
    }

    @Test
    void testCancelOrder_OrderNotFound() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.cancelOrder(1));
    }
}
