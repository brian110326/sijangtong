package com.example.sijangtong.service;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class SijangtongServiceTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private StoreService storeService;

  @Autowired
  private OrderItemService orderItemService;

  // 5월 17 일 확인 완료
  @Test
  public void insertProductTest() {
    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto productImgDto = ProductImgDto
        .builder()
        .uuid("테스트 메인")
        .imgName("메인 이미지이름1234")
        .path("메인 패스1")
        .build();
    imgList.add(productImgDto);

    ProductDto productDto = new ProductDto();
    productDto.setAmount(10000);
    productDto.setPName("메인");
    productDto.setPrice(100000);
    productDto.setStoreId(10L);
    productDto.setProductImgDtos(imgList);

    productService.productInsert(productDto);
  }

  // 5월 17 일 확인 완료
  @Test
  public void updateProduct() {
    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto productImgDto = ProductImgDto
        .builder()
        .uuid("메인 업데이트")
        .imgName("메인 업데이트")
        .path("메인 패스 업데이트")
        .build();
    imgList.add(productImgDto);

    ProductDto productDto = new ProductDto();
    productDto.setAmount(123);
    productDto.setPName("메인");
    productDto.setPrice(1000);
    productDto.setStoreId(10L);
    productDto.setProductId(220L);
    productDto.setProductImgDtos(imgList);

    productService.productUpdate(productDto);
  }

  // 5월 17 일 확인 완료
  @Test
  public void insetStoreTest() {
    List<StoreImgDto> imgList = new ArrayList<>();
    StoreImgDto storeImgDto = StoreImgDto
        .builder()
        .stUuid("메인 uuid")
        .stImgName("메인 이미지 이름123")
        .stPath("메인 패스")
        .build();
    imgList.add(storeImgDto);

    StoreDto storeDto = new StoreDto();
    storeDto.setStoreCategory(StoreCategory.CLOTH);
    storeDto.setOpenTime("메인 오픈");
    storeDto.setCloseTime("메인 영업종료");
    storeDto.setStoreAddress("메인");
    storeDto.setStoreName("메인 가게 이름");
    storeDto.setStoreDetail("메인 스토어 디테일");
    storeDto.setGradeAvg(4.1);
    storeDto.setStoreTel("메인");
    storeDto.setStoreImgDtos(imgList);

    storeService.storeInsert(storeDto);
  }

  // 5월 17 일 확인 완료

  @Test
  public void updateStoreTest() {
    List<StoreImgDto> imgList = new ArrayList<>();
    StoreImgDto storeImgDto = StoreImgDto
        .builder()
        .stUuid(" 메인 업데이트 테스트 uuid")
        .stImgName(" 메인업데이트테스트 이미지 이름123")
        .stPath("메인  업데이트테스트 패스")
        .build();
    imgList.add(storeImgDto);

    StoreDto storeDto = new StoreDto();
    storeDto.setStoreId(27L);
    storeDto.setStoreCategory(StoreCategory.CLOTH);
    storeDto.setOpenTime(" 메인 123 업데이트오픈");
    storeDto.setCloseTime(" 메인  업데이트영업종료");
    storeDto.setStoreAddress(" 메인 업데이트");
    storeDto.setStoreName("4 메인  업데이트가게 이름");
    storeDto.setStoreDetail(" 메인  업데이트스토어 디테일");
    storeDto.setGradeAvg(1.0);
    storeDto.setStoreTel("메인 업데이트 01053859803");
    storeDto.setStoreImgDtos(imgList);
    // storeService.storeUpdate(storeDto);
  }

  // 오더 아이템 생성
  @Test
  public void createOrderItem() {
    orderItemService.createOrderItem(13, 852L, "ksb1234@naver.com", 5L);
  }

  @Transactional
  @Test
  public void OrderBuyItemList() {
    System.out.println(
        orderItemService.getDeliveringOrderItems("ksb1234@naver.com"));
  }

  @Transactional
  @Test
  public void OrderItemList() {
    System.out.println(
        orderItemService.getMemberOrderItems("ksb1234@naver.com"));
  }

}
