package com.example.sijangtong.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.StoreImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreImgRepository storeImgRepository;

    @Override
    public PageResultDto<StoreDto, Object[]> getStoreList(PageRequestDto pageRequestDto) {
        Page<Object[]> result = storeImgRepository
                .getTotalList(pageRequestDto.getPageable(Sort.by("store_id").descending()));

        Function<Object[], StoreDto> fn = (en -> entityToDto((Store) en[0],
                (List<StoreImg>) Arrays.asList((StoreImg) en[1]), (Double) en[2]));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public StoreDto getRow(Long storeId) {
        List<Object[]> result = storeImgRepository.getStoreRow(storeId);

        Store store = (Store) result.get(0)[0];
        Double avg = (Double) result.get(0)[2];

        // List<StoreImg> list = new ArrayList<>();
        // result.forEach(arr -> {
        // StoreImg storeImg = (StoreImg) arr[1];
        // list.add(storeImg);
        // });

        List<StoreImg> list = result.stream().map(arr -> (StoreImg) arr[1]).collect(Collectors.toList());

        return entityToDto(store, list, avg);
    }

}
