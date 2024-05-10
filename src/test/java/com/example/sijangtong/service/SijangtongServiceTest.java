package com.example.sijangtong.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class SijangtongServiceTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void storeList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Object> list = storeRepository.getListPage(pageRequest);

        list.forEach(store -> System.out.println(store));
    }

}
