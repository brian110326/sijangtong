package com.example.sijangtong.service;

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
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class SijangtongServiceTest {

    @Autowired
    private StoreImgRepository storeImgRepository;

    @Autowired
    private ProductImgRepository productImgRepository;

    @Test
    public void storeList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();

        Page<Object[]> list = storeImgRepository.getTotalList(requestDto.getPageable(Sort.by("storeId").descending()));
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void getStoreRow() {
        List<Object[]> list = storeImgRepository.getStoreRow(282L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void productList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> list = productImgRepository.getProductList(requestDto.getPageable(Sort.by("productId")), 44L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void getProductRow() {
        List<Object[]> list = productImgRepository.getProductRow(7L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

}
