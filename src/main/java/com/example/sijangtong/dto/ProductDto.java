package com.example.sijangtong.dto;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Store;

//import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import jakarta.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Long productId;

  @NotBlank(message = "상품 이름은 필수입니다")
  private String pName;

  @NotNull(message = "상품 가격은 필수입니다")
  @Positive
  private Integer price;

  @Positive(message = "재고는 필수 입니다")
  private int amount;

  private Long storeId;

  private double avg;

  private Long orderItemId;

  // 1개의 product에 여러장의 productimg
  // product를 기준으로 productimg를 찾을 수도 있으니
  @Builder.Default
  private List<ProductImgDto> productImgDtos = new ArrayList<>();
}
