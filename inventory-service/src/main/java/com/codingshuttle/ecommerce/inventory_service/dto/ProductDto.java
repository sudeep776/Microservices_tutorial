package com.codingshuttle.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private String title;

    private double price;

    private Integer stock;
}
