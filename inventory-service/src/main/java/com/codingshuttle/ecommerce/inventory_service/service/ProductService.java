package com.codingshuttle.ecommerce.inventory_service.service;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllInventory();
    public ProductDto getProductById(Long id);

    Double reduceStocks(OrderRequestDto orderRequestDto);
}
