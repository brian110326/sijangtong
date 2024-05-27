package com.example.sijangtong.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.sijangtong.constant.StoreCategory;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Long storeId;

    private StoreCategory storeCategory;

    @NotBlank(message = "StoreTel cannot be blank")
    private String storeTel;

    @NotBlank(message = "Opentime cannot be blank")
    private String openTime;

    @NotBlank(message = "Closetime cannot be blank")
    private String closeTime;

    @NotBlank(message = "StoreAddress cannot be blank")
    private String storeAddress;

    @NotBlank(message = "StoreName cannot be blank")
    private String storeName;

    private String storeDetail;

    private double gradeAvg;

    @Builder.Default
    private List<StoreImgDto> storeImgDtos = new ArrayList<>();
}
