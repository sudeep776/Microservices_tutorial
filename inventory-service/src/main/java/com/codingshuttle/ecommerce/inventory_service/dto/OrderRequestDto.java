package com.codingshuttle.ecommerce.inventory_service.dto;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private List<OrderRequestItemDto> items;
}
