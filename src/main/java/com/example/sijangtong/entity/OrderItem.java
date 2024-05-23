package com.example.sijangtong.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "product", "order" })
@Entity
public class OrderItem {

  @Id
  @Column(name = "order_item_id")
  @SequenceGenerator(
    name = "store_order_item_seq_gen",
    sequenceName = "store_order_item_seq",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "store_order_item_seq_gen"
  )
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Order order;

  private int orderPrice; // 주문가격

  private int orderAmount; // 수량
}
