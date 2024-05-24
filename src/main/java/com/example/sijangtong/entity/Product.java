package com.example.sijangtong.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@ToString(exclude = { "store", "orderItem" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

  @Id
  @SequenceGenerator(name = "store_product_seq_gen", sequenceName = "store_product_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_product_seq_gen")
  private Long productId;

  private String pName;

  private int price;

  private int amount;

  // 구매 물품 수량
  @OneToOne
  private OrderItem orderItem;

  @ManyToOne(fetch = FetchType.LAZY)
  private Store store;
}
