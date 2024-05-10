package com.example.sijangtong.service;

import java.util.List;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;

public interface StoreService {

    PageResultDto<StoreDto, Object[]> getStoreList(PageRequestDto pageRequestDto);

    public default StoreDto entityToDto(Store store, List<StoreImg> storeImgs) {
        StoreDto storeDto = StoreDto.builder().storeId(store.getStoreId())
                .storeCategory(store.getStoreCategory())
                .storeTel(store.getStoreTel())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .storeAddress(store.getStoreAddress())
                .storeName(store.getStoreName())
                .storeDetail(store.getStoreDetail())
                .build();

        return storeDto;
    }

    public default Store dtoToEntity(StoreDto storeDto) {
        Store store = Store.builder().storeId(storeDto.getStoreId())
                .storeCategory(storeDto.getStoreCategory())
                .storeTel(storeDto.getStoreTel())
                .openTime(storeDto.getOpenTime())
                .closeTime(storeDto.getCloseTime())
                .storeAddress(storeDto.getStoreAddress())
                .storeName(storeDto.getStoreName())
                .storeDetail(storeDto.getStoreDetail())
                .build();

        return store;
    }
}
