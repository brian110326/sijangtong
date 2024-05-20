package com.example.sijangtong.service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.stream.Collectors;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;

public interface StoreService {

        // store list 보여주기
        PageResultDto<StoreDto, Object[]> getStoreList(PageRequestDto pageRequestDto);

        // PageResultDto<StoreDto, Object[]> getStoreListByCategory(PageRequestDto
        // pageRequestDto,
        // StoreCategory storeCategory);

        // store 상세조회
        StoreDto getRow(Long storeId);

        // store 삭제
        Long removeStore(Long storeId);

        Long storeInsert(StoreDto storeDto);

        Long storeUpdate(StoreDto storeDto);

        // store의 list를 보여주기 위한 dto변환
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

        public default Map<String, Object> dtoToentity(StoreDto storeDto) {
                Map<String, Object> entityMap = new HashMap<>();

                Store store = Store.builder()
                                .storeId(storeDto.getStoreId())
                                .storeCategory(storeDto.getStoreCategory())
                                .storeTel(storeDto.getStoreTel())
                                .openTime(storeDto.getOpenTime())
                                .closeTime(storeDto.getCloseTime())
                                .storeAddress(storeDto.getStoreAddress())
                                .storeName(storeDto.getStoreName())
                                .storeDetail(storeDto.getStoreDetail()).build();

                entityMap.put("store", store);

                List<StoreImgDto> storeImgDtos = storeDto.getStoreImgDtos();

                if (storeImgDtos != null && storeImgDtos.size() > 0) {
                        List<StoreImg> storeImgs = storeImgDtos.stream().map(sDto -> {
                                StoreImg storeImg = StoreImg.builder()
                                                .stImgName(sDto.getStImgName())
                                                .stUuid(sDto.getStUuid())
                                                .stPath(sDto.getStPath())
                                                .store(store)
                                                .build();
                                return storeImg;
                        }).collect(Collectors.toList());

                        entityMap.put("imgList", storeImgs);
                }

                return entityMap;
        }

}
