package com.example.sijangtong.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class SijangtongServiceTest {

    @Autowired
    private StoreImgRepository storeImgRepository;

    @Test
    public void storeList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();

        Page<Object[]> list = storeImgRepository.getTotalList(requestDto.getPageable(Sort.by("store_id")));
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

    }

}
