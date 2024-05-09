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

    private String storeTel;

    private String openTime;
    private String closeTime;

    private String storeAddress;

    private String storeName;

    private String storeDetail;
}
