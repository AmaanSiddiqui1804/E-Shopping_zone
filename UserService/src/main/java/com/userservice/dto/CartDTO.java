package com.userservice.dto;

import java.util.List;

public class CartDTO {

    private int cartId;

    private double totalPrice;

    private List<ItemsDTO> items;

    private int customerId;

    public CartDTO(int cartId, List<ItemsDTO> items) {
        this.cartId = cartId;
        this.items = items;
    }
}
