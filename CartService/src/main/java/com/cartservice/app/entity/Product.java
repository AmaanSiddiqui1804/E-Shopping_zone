package com.cartservice.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productId;

    private String productType;

    private String productName;

    private String category;

    private String image;

    private double price;

    private String description;

    private double discount;
}
