package com.cartservice.app.service;


import com.cartservice.app.entity.Cart;
import com.cartservice.app.entity.Items;
import com.cartservice.app.entity.Product;
import com.cartservice.app.exception.CartServiceException;
import com.cartservice.app.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
   // private ProductFeignClient productFeignClient;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart testCart;
    private List<Cart> testCarts;

    @BeforeEach
    public void setup() {
        // Initialize test data before each test method
        testCart = new Cart();
        testCart.setCartId(1);
        testCart.setCustomerId(123);
        testCart.setTotalPrice(0); // Set your initial price if needed

        Items item1 = new Items();
        item1.setItemId(1);
        item1.setProductId(101);
        item1.setPrice(50.0);
        item1.setQuantity(2);

        Items item2 = new Items();
        item2.setItemId(2);
        item2.setProductId(102);
        item2.setPrice(75.0);
        item2.setQuantity(1);

        List<Items> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        testCart.setItems(items);

        testCarts = new ArrayList<>();
        testCarts.add(testCart);
    }

    @Test
    public void testGetCartById_ExistingId_ReturnsCart() {
        // Mock repository response
        when(cartRepository.findById(1)).thenReturn(Optional.of(testCart));

        // Call service method
        ResponseEntity<Cart> response = cartService.getcartById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(123, response.getBody().getCustomerId());
    }

    @Test
    public void testGetCartById_NonExistingId_ThrowsException() {
        // Mock repository response
        when(cartRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        CartServiceException exception = assertThrows(CartServiceException.class, () -> cartService.getcartById(999));
        assertEquals("Cart not found with ID 999", exception.getMessage());
    }


    @Test
    public void testUpdateCart_NonExistingId_ThrowsException() {
        // Mock repository response
        when(cartRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        CartServiceException exception = assertThrows(CartServiceException.class, () -> cartService.updateCart(999, testCart));
        assertEquals("Cart Id not present", exception.getMessage());
    }

    @Test
    public void testGetAllCarts_ReturnsListOfCarts() {
        // Mock repository response
        when(cartRepository.findAll()).thenReturn(testCarts);

        // Call service method
        ResponseEntity<List<Cart>> response = cartService.getallcarts();

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetAllCarts_EmptyList_ThrowsException() {
        // Mock repository response
        when(cartRepository.findAll()).thenReturn(new ArrayList<>());

        // Call service method and verify exception
        CartServiceException exception = assertThrows(CartServiceException.class, () -> cartService.getallcarts());
        assertEquals("No any data present", exception.getMessage());
    }

    @Test
    public void testAddCart_ValidCart_ReturnsSavedCart() {
        // Mock repository save
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);

        // Mock Feign client responses
        Product product1 = new Product();
        product1.setProductId(101);
        product1.setPrice(25.0);

        Product product2 = new Product();
        product2.setProductId(102);
        product2.setPrice(30.0);

       // when(productFeignClient.getProductById(101)).thenReturn(ResponseEntity.ok(product1));
       // when(productFeignClient.getProductById(102)).thenReturn(ResponseEntity.ok(product2));

        // Call service method
        ResponseEntity<Cart> response = cartService.addCart(testCart);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    public void testCartTotal_CalculatesCorrectTotal() {
//        // Call service method
//        double total = cartService.cartTotal(testCart);
//
//        // Verify the result
//        assertEquals(175.0, total);
//    }



    @Test
    public void testDeleteCart_NonExistingId_ThrowsException() {
        // Mock repository response
        when(cartRepository.findById(999)).thenReturn(Optional.empty());

        // Call service method and verify exception
        CartServiceException exception = assertThrows(CartServiceException.class, () -> cartService.deleteCart(999));
        assertEquals("Cart Id not present", exception.getMessage());
    }

    @Test
    public void testGetAllByCustomerId_ExistingCustomerId_ReturnsListOfCarts() {
        // Mock repository response
        when(cartRepository.findByCustomerId(123)).thenReturn(testCarts);

        // Call service method
        ResponseEntity<List<Cart>> response = cartService.getAllByCustomerId(123);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetAllByCustomerId_NonExistingCustomerId_ThrowsException() {
        // Mock repository response
        when(cartRepository.findByCustomerId(999)).thenReturn(new ArrayList<>());

        // Call service method and verify exception
        CartServiceException exception = assertThrows(CartServiceException.class, () -> cartService.getAllByCustomerId(999));
        assertEquals("No Items", exception.getMessage());
    }


}
