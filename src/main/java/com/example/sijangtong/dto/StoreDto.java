package com.example.sijangtong.dto;

import com.example.sijangtong.constant.StoreCategory;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
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

    // StoreInfo와의 관계
    private Long storeInfoId;
}
