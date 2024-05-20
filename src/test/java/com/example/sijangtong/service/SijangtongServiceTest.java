package com.example.sijangtong.service;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import com.example.sijangtong.repository.RiderRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;
import groovyjarjarantlr4.v4.parse.ANTLRParser.prequelConstruct_return;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class SijangtongServiceTest {

  @Autowired
  private StoreImgRepository storeImgRepository;

  @Autowired
  private ProductImgRepository productImgRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private RiderRepository riderRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private StoreService storeService;

  @Test
  public void getStoreRow() {
    List<Object[]> list = storeImgRepository.getStoreRow(40L);

    for (Object[] objects : list) {
      System.out.println(Arrays.toString(objects));
    }
  }

  @Test
  public void insertProductTest() {
    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto productImgDto = ProductImgDto
      .builder()
      .uuid("테스트 uuid1")
      .imgName("테스트 이미지이름1234")
      .path("테스트 패스1")
      .build();
    imgList.add(productImgDto);

    ProductDto productDto = new ProductDto();
    productDto.setAmount(10000);
    productDto.setPName("테스트상품1");
    productDto.setPrice(100000);
    productDto.setStoreId(10L);
    productDto.setProductImgDtos(imgList);

    productService.productInsert(productDto);
  }

  @Test
  public void updateProduct() {
    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto productImgDto = ProductImgDto
      .builder()
      .uuid("테스트 업데이트")
      .imgName("테스트 업데이트")
      .path("테스트 패스 업데이트")
      .build();
    imgList.add(productImgDto);

    ProductDto productDto = new ProductDto();
    productDto.setAmount(123);
    productDto.setPName("테스트상품");
    productDto.setPrice(1000);
    productDto.setStoreId(10L);
    productDto.setProductId(28L);
    productDto.setProductImgDtos(imgList);

    productService.productUpdate(productDto);
  }

  //
  //
  //
  @Test
  public void insetStoreTest() {
    List<StoreImgDto> imgList = new ArrayList<>();
    StoreImgDto storeImgDto = StoreImgDto
      .builder()
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

    storeService.storeInsert(storeDto);
  }

  @Test
  public void updateStoreTest() {
    List<StoreImgDto> imgList = new ArrayList<>();
    StoreImgDto storeImgDto = StoreImgDto
      .builder()
      .stUuid(" 4수정 테스트 uuid")
      .stImgName(" 4수정 테스트 이미지 이름123")
      .stPath("4수정 테스트 패스")
      .build();
    imgList.add(storeImgDto);

    StoreDto storeDto = new StoreDto();
    storeDto.setStoreId(203L);
    storeDto.setStoreCategory(StoreCategory.CLOTH);
    storeDto.setOpenTime(" 4수정테스트 오픈");
    storeDto.setCloseTime(" 5수정테스트 영업종료");
    storeDto.setStoreAddress(" 45수정솔데스크");
    storeDto.setStoreName("4 5수정테스트 가게 이름");
    storeDto.setStoreDetail(" 45수정테스트 스토어 디테일");
    storeDto.setGradeAvg(1.0);
    storeDto.setStoreTel("4수정 01053859803");
    storeDto.setStoreImgDtos(imgList);

    storeService.storeUpdate(storeDto);
  }
}
