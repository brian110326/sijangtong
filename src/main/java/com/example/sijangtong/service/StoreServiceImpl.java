package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
                (List<StoreImg>) Arrays.asList((StoreImg) en[1])));

        return new PageResultDto<>(result, fn);
    }

}
