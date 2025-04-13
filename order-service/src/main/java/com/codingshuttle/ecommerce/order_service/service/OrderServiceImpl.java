package com.codingshuttle.ecommerce.order_service.service;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_service.entity.Orders;
import com.codingshuttle.ecommerce.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
        List<Orders> orders = orderRepository.findAll();
        return orders.stream().map(order->modelMapper.map(order,OrderRequestDto.class)).toList();
    }

    @Override
    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching order with Id : {}",id);
        Orders order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        return modelMapper.map(order,OrderRequestDto.class);
    }
}
