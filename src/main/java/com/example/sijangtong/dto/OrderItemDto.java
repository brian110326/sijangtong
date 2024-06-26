package com.example.sijangtong.dto;

import com.example.sijangtong.constant.OrderSatetus;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  private Long orderId;

  private int orderPrice; // 주문가격

  private int orderAmount; // 수량

  // 임시추가
  private Long productId;

  private OrderSatetus orderSatetus;

  private String pName;

  private Integer price;

  private Long storId;
}
