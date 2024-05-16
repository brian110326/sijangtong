package com.example.sijangtong.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;

public interface StoreService {

        PageResultDto<StoreDto, Object[]> getStoreList(PageRequestDto pageRequestDto);

        PageResultDto<StoreDto, Object[]> getStoreListByCategory(PageRequestDto pageRequestDto,
                        StoreCategory storeCategory);

        PageResultDto<StoreDto, Object[]> getStoreListByAddress(PageRequestDto pageRequestDto,
                        String storeAddress);

        StoreDto getRow(Long storeId);

        Long removeStore(Long storeId);

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

                List<StoreImgDto> storeImgDtos = storeImgs.stream().map(storeImg -> {
                        return StoreImgDto.builder().storeImgId(storeImg.getStoreImgId()).stUuid(storeImg.getStUuid())
                                        .stImgName(storeImg.getStImgName()).stPath(storeImg.getStPath())
                                        .build();
                }).collect(Collectors.toList());

                storeDto.setStoreImgDtos(storeImgDtos);

                return storeDto;
        }

}
