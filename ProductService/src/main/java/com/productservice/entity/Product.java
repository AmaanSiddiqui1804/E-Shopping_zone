package com.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "Product type cannot be blank")
    private String productType;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    private String image;

    @NotNull(message = "Price cannot be null")
    private double price;

    private String description;

    private double discount;
}
