package com.example.sijangtong.dto;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Store;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Long productId;

  @NotBlank(message = "Product Name cannot be blank")
  private String pName;

  @NotNull(message = "Price cannot be blank. Do you mean by numer '0'?")
  private Integer price;

  private int amount;

  private Long storeId;

  private double avg;

  private Long orderItemId;

  // 1개의 product에 여러장의 productimg
  // product를 기준으로 productimg를 찾을 수도 있으니
  @Builder.Default
  private List<ProductImgDto> productImgDtos = new ArrayList<>();
}
