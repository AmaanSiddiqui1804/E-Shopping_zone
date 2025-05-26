//package com.cartservice.app.controller;
// 
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
// 
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
// 
//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
// 
//import com.cartservice.app.controller.CartController;
//import com.cartservice.app.entity.Cart;
//import com.cartservice.app.entity.Items;
//import com.cartservice.app.service.CartService;
// 
//public class CartControllerTest {
// 
//    @Mock
//    private CartService cartService;
// 
//    @InjectMocks
//    private CartController cartController;
// 
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
// 
//    @Test
//    public void testGetCartById() {
//        int cartId = 1;
//        List<Items>l=new ArrayList<>();
//        Cart mockCart = new Cart(cartId, l); // Assuming constructor parameters
//        when(cartService.getcartById(cartId)).thenReturn(ResponseEntity.ok(mockCart));
// 
//        ResponseEntity<Cart> response = cartController.getCart(cartId);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockCart, response.getBody());
//    }
// 
//    @Test
//    public void testUpdateCart() {
//        int cartId = 1;
//        List<Items>l=new ArrayList<>();
//        Cart updatedCart = new Cart(cartId, l); // Assuming constructor parameters
//        when(cartService.updateCart(cartId, updatedCart)).thenReturn(ResponseEntity.ok(updatedCart));
// 
//        ResponseEntity<Cart> response = cartController.updateCart(cartId, updatedCart);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedCart, response.getBody());
//    }
// 
//    @Test
//    public void testGetAllCarts() {
//    	List<Items>l=new ArrayList<>();
//        List<Cart> mockCarts = Arrays.asList(new Cart(1, l), new Cart(2, l)); // Assuming constructor parameters
//        when(cartService.getallcarts()).thenReturn(ResponseEntity.ok(mockCarts));
// 
//        ResponseEntity<List<Cart>> response = cartController.getAllCarts();
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockCarts, response.getBody());
//    }
// 
//    @Test
//    public void testGetAllByCustomerId() {
//        int customerId = 1;
//        List<Items>l=new ArrayList<>();
//        List<Cart> mockCarts = Arrays.asList(new Cart(1, l), new Cart(2, l)); // Assuming constructor parameters
//        when(cartService.getAllByCustomerId(customerId)).thenReturn(ResponseEntity.ok(mockCarts));
// 
//        ResponseEntity<List<Cart>> response = cartController.getAllByCustomerId(customerId);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockCarts, response.getBody());
//    }
// 
//    @Test
//    public void testAddCart() {
//    	List<Items>l=new ArrayList<>();
//        Cart newCart = new Cart(1, l); // Assuming constructor parameters
//        when(cartService.addCart(newCart)).thenReturn(ResponseEntity.ok(newCart));
// 
//        ResponseEntity<Cart> response = cartController.addCart(newCart);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(newCart, response.getBody());
//    }
// 
//    @Test
//    public void testDeleteCart() {
//        int cartId = 1;
//        String successMessage = "Cart deleted successfully";
//        when(cartService.deleteCart(cartId)).thenReturn(ResponseEntity.ok(successMessage));
// 
//        ResponseEntity<String> response = cartController.deleteCart(cartId);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(successMessage, response.getBody());
//    }
//}