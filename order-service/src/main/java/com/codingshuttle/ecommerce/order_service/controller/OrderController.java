package com.codingshuttle.ecommerce.order_service.controller;

import com.codingshuttle.ecommerce.order_service.clients.InventoryClient;
import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final InventoryClient inventoryClient;

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }

    @GetMapping("/test")
    public String test(){
        return inventoryClient.testOrders();
    }
}
