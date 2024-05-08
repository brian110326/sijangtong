package com.example.sijangtong.dto;

import java.time.LocalDateTime;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.entity.Member;

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

    private Member member;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
