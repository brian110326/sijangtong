package com.example.sijangtong.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;

    @Builder.Default
    private List<ProductDto> productDtos = new ArrayList<>();

    private Long orderId;

    private int orderPrice; // 주문가격

    private int orderAmount; // 수량

}
