package com.example.sijangtong.repository;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import groovy.transform.AutoImplement;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductImgRepository productImgRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private StoreImgRepository storeImgRepository;

  @Autowired
  private RiderRepository riderRepository;

  @Test
  public void insertMemberTest() {
    IntStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Member member = Member
          .builder()
          .memberEmail("member" + i + "@naver.com")
          .memberNickname("이것은 닉네임..")
          .memberAddress("종로")
          .memberPwd("1111")
          .memberRole(MemberRole.MEMBER)
          .build();

        memberRepository.save(member);
      });
  }

  @Test
  public void insertOrderTest() {
    LongStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Member member = Member
          .builder()
          .memberEmail("member" + i + "@naver.com")
          .build();
        Order order = Order
          .builder()
          .orderAddress("강동구")
          .orderPayment(OrderPayment.CASH)
          .member(member)
          .build();

        orderRepository.save(order);
      });
  }

  @Test
  public void insertOrderItemTest() {
    LongStream
      .rangeClosed(2, 200)
      .forEach(i -> {
        Order order = Order.builder().orderId(i).build();
        Product product = Product.builder().productId(i).build();

        OrderItem orderItem = OrderItem
          .builder()
          .product(product)
          .order(order)
          .orderPrice(25000)
          .orderAmount((int) (Math.random() * 15) + 1)
          .build();
        orderItemRepository.save(orderItem);
      });
  }

  @Test
  public void insertStoreTest() {
    LongStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Store store = Store
          .builder()
          .storeCategory(StoreCategory.SEAFOOD)
          .storeTel("010-1111-1" + i)
          .openTime("6 시에 오픈")
          .closeTime("10시에 마감")
          .storeAddress("종로")
          .storeName("이것은 가계요" + i)
          .storeDetail("이 가계는....")
          .build();

        storeRepository.save(store);

        int count = (int) (Math.random() * 5) + 1;

        for (int j = 0; j < count; j++) {
          StoreImg storeImg = StoreImg
            .builder()
            .stUuid(UUID.randomUUID().toString())
            .stPath(null)
            .stImgName("stImg" + i + ".jpg")
            .store(store)
            .build();

          storeImgRepository.save(storeImg);
        }
      });
  }

  @Test
  public void insertStoreImgTest() {}

  @Test
  public void insertProductTest() {
    LongStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Store store = Store.builder().storeId(i).build();

        Product product = Product
          .builder()
          .pName("재고" + i)
          .price(5000)
          .amount(20)
          .store(store)
          .build();

        productRepository.save(product);

        int count = (int) (Math.random() * 5) + 1;

        for (int j = 0; j < count; j++) {
          ProductImg productImg = ProductImg
            .builder()
            .uuid(UUID.randomUUID().toString())
            .path(null)
            .imgName("img" + i + ".jpg")
            .product(product)
            .build();
          productImgRepository.save(productImg);
        }
      });
  }

  @Test
  public void riderInsertTest() {
    LongStream
      .rangeClosed(1, 100)
      .forEach(i -> {
        Rider rider = Rider
          .builder()
          .riderId(i)
          .riderName("Rider" + i)
          .riderTel("010-1234-5678")
          .riderStatus(RiderStatus.DELIVERING)
          .build();

        riderRepository.save(rider);
      });
  }
}
