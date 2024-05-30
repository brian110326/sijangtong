package com.example.sijangtong.entity;

import com.example.sijangtong.constant.StoreCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store {

  @Id
  @SequenceGenerator(
    name = "store_seq_gen",
    sequenceName = "store_seq",
    allocationSize = 1,
    initialValue = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "store_seq_gen"
  )
  private Long storeId;

  @Enumerated(EnumType.STRING)
  private StoreCategory storeCategory;

  private String storeTel;

  private String openTime;
  private String closeTime;

  private String storeAddress;

  private String storeName;

  private String storeDetail;

  @OneToOne(mappedBy = "store")
  private Member member;
}
