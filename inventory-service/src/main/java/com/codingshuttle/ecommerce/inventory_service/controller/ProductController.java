package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> productDtoList = productService.getAllInventory();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/test")
    public String Test(){
        return "Hello from Inventory Products";
    }

    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }

}
