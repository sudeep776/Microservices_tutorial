package com.codingshuttle.ecommerce.order_service.clients;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service",path = "/inventory")
public interface InventoryClient {
    @GetMapping("/products/test")
    public String testOrders();

    @PutMapping("/products/reduce-stocks")
    Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);
}
