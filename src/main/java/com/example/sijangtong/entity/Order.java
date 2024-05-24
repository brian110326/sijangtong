package com.example.sijangtong.entity;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.constant.RiderOrdercancel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "member", "rider", "store" })
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Builder
@Entity
public class Order extends BaseEntity {

  @Id
  @SequenceGenerator(
    name = "order_seq_gen",
    sequenceName = "order_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "order_seq_gen"
  )
  private Long orderId;

  private String orderAddress;

  @Enumerated(EnumType.STRING)
  private OrderPayment orderPayment;

  // 라이더측 주문 취소 컬럼
  @Enumerated(EnumType.STRING)
  private RiderOrdercancel riderOrdercancel;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  private Store store;

  @OneToOne(mappedBy = "order")
  private Rider rider;
}
