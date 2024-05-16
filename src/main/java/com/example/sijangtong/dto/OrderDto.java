package com.example.sijangtong.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Rider;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;

    private String orderAddress;

    private OrderPayment orderPayment;

    private String memberEmail;

    private Long storeId;

    private Rider rider;

    @Builder.Default
    private List<OrderItemDto> orderItemDtos = new ArrayList<>();

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
