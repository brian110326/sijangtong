package com.example.sijangtong.service;

import java.util.List;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;

public interface StoreService {

    PageResultDto<StoreDto, Object[]> getStoreList(PageRequestDto pageRequestDto);

    StoreDto getRow(Long storeId);

    Long removeStore(Long storeId);

    public default StoreDto entityToDto(Store store, List<StoreImg> storeImgs, Double avg) {
        StoreDto storeDto = StoreDto.builder().storeId(store.getStoreId())
                .storeCategory(store.getStoreCategory())
                .storeTel(store.getStoreTel())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .storeAddress(store.getStoreAddress())
                .storeName(store.getStoreName())
                .storeDetail(store.getStoreDetail())
                .gradeAvg(avg != null ? avg : 0.0d)
                .build();

        return storeDto;
    }

}
