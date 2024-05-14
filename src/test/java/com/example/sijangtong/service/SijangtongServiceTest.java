package com.example.sijangtong.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@SpringBootTest
public class SijangtongServiceTest {

    @Autowired
    private StoreImgRepository storeImgRepository;

    @Autowired
    private ProductImgRepository productImgRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private StoreServiceImpl storeServiceImpl;

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

    @Test
    public void insertProductTest() {

        List<ProductImgDto> imgList = new ArrayList<>();
        ProductImgDto productImgDto = ProductImgDto.builder()
                .uuid("테스트 uuid")
                .imgName("테스트 이미지이름123")
                .path("테스트 패스")
                .build();
        imgList.add(productImgDto);

        ProductDto productDto = new ProductDto();
        productDto.setAmount(10);
        productDto.setPName("테스트상품");
        productDto.setPrice(1000);
        productDto.setStoreId(10L);
        productDto.setProductImgDtos(imgList);

        productServiceImpl.productInsert(productDto);

    }

    @Test
    public void insetStoreTest() {
        List<StoreImgDto> imgList = new ArrayList<>();
        StoreImgDto storeImgDto = StoreImgDto.builder()
                .stUuid("테스트 uuid")
                .stImgName("테스트 이미지 이름123")
                .stPath("테스트 패스")
                .build();
        imgList.add(storeImgDto);

        StoreDto storeDto = new StoreDto();
        storeDto.setStoreCategory(StoreCategory.CLOTH);
        storeDto.setOpenTime("테스트 오픈");
        storeDto.setCloseTime("테스트 영업종료");
        storeDto.setStoreAddress("솔데스크");
        storeDto.setStoreName("테스트 가게 이름");
        storeDto.setStoreDetail("테스트 스토어 디테일");
        storeDto.setGradeAvg(4.1);
        storeDto.setStoreTel("01053859803");
        storeDto.setStoreImgDtos(imgList);

        storeServiceImpl.storeInsert(storeDto);

    }

    @Test
    public void updateStoreTest() {

        List<StoreImgDto> imgList = new ArrayList<>();
        StoreImgDto storeImgDto = StoreImgDto.builder()

                .stUuid(" 4수정 테스트 uuid")
                .stImgName(" 4수정 테스트 이미지 이름123")
                .stPath("4수정 테스트 패스")
                .build();
        imgList.add(storeImgDto);

        StoreDto storeDto = new StoreDto();
        storeDto.setStoreId(203L);
        storeDto.setStoreCategory(StoreCategory.CLOTH);
        storeDto.setOpenTime(" 4수정테스트 오픈");
        storeDto.setCloseTime(" 4수정테스트 영업종료");
        storeDto.setStoreAddress(" 4수정솔데스크");
        storeDto.setStoreName("4 수정테스트 가게 이름");
        storeDto.setStoreDetail(" 4수정테스트 스토어 디테일");
        storeDto.setGradeAvg(1.0);
        storeDto.setStoreTel("4수정 01053859803");
        storeDto.setStoreImgDtos(imgList);

        storeServiceImpl.storeUpdate(storeDto);

    }

}
