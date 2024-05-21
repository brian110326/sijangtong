package com.example.sijangtong.dto;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.constant.RiderOrdercancel;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Rider;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  private String memberEmail;

  private Long storeId;

  private Rider rider;

  @Builder.Default
  private List<OrderItemDto> orderItemDtos = new ArrayList<>();

  private Member member;

  private OrderPayment orderPayment;

  private LocalDateTime createdDate;
  // 라이더측 배달 취소 사유
  private RiderOrdercancel riderOrdercancel;

  private OrderPayment orderPayment;

  private LocalDateTime lastModifiedDate;
}
