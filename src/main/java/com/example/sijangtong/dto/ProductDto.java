package com.example.sijangtong.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Store;

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
public class ProductDto {

    private Long productId;

    private String pName;

    private int price;

    private int amount;

    private Long storeId;

    // 1개의 product에 여러장의 productimg
    // product를 기준으로 productimg를 찾을 수도 있으니
    @Builder.Default
    private List<ProductImgDto> productImgDtos = new ArrayList<>();
}
