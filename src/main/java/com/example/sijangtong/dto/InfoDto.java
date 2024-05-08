package com.example.sijangtong.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoDto {
    private String storeInfoId;

    private String storeTel;

    private String storeTime;

    private String storeAddress;

    private String storeName;

    private String storeDetail;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
