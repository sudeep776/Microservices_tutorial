package com.codingshuttle.ecommerce.inventory_service.service;


import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> getAllInventory() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(product->{
            return modelMapper.map(product,ProductDto.class);
        }).toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Fetching product with ID : {}",id);
        Optional<Product> product = productRepository.findById(id);
        return product.map(item ->modelMapper.map(item,ProductDto.class)).orElseThrow(()->new RuntimeException("Inventory not found"));
    }

    @Override
    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        //for all the items in the order we need to reduce stock
        log.info("reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDto orderRequestDto1 : orderRequestDto.getItems()){
            Long productId = orderRequestDto1.getProductId();
            Integer quantity = orderRequestDto1.getQuantity();
            Product product = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found with id : "+productId));

            if(product.getStock() < quantity){
                throw new RuntimeException("Product cannot be fulfilled for given quantity");
            }
            product.setStock(product.getStock()-quantity);
            totalPrice+=quantity*product.getPrice();
        }return totalPrice;
    }
}
