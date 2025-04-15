package com.codingshuttle.ecommerce.order_service.service;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_service.entity.Orders;

import java.util.List;

public interface OrderService {
    public List<OrderRequestDto> getAllOrders();
    public OrderRequestDto getOrderById(Long id);

    OrderRequestDto createOrder(OrderRequestDto orderRequestDto);
}
