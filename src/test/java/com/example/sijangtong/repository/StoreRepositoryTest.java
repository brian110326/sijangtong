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
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.UUID;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class StoreRepositoryTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

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
      .rangeClosed(1, 5)
      .forEach(i -> {
        Member member = Member
          .builder()
          .memberEmail("member" + i + "@naver.com")
          .memberNickname("이것은 닉네임..")
          .memberAddress("종로")
          .memberPwd(passwordEncoder.encode("1111"))
          .memberRole(MemberRole.ADMIN)
          .build();

        memberRepository.save(member);
      });
  }

  @Test
  public void insertOrderTest() {
    LongStream
      .rangeClosed(300, 304)
      .forEach(i -> {
        Member member = Member
          .builder()
          .memberEmail("member1@naver.com")
          .build();
        Store store = Store.builder().storeId(1L).build();
        // Rider rider = Rider
        // .builder()
        // .riderId(i)
        // .riderName("Rider" + i)
        // .riderTel("010-1234-5678")
        // .riderStatus(RiderStatus.DELIVERING)
        // .build();

        // // riderRepository.save(rider);
        Order order = Order
          .builder()
          .orderAddress("강동구")
          .orderPayment(OrderPayment.CASH)
          .member(member)
          .store(store)
          // .rider(rider)
          .build();
        orderRepository.save(order);
      });
  }

  @Test
  public void insertOrderItemTest() {
    LongStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Order order = Order.builder().orderId(i).build();
        Product product = Product.builder().productId(i).build();
        // OrderItem orderItem = OrderItem
        // .builder()
        // .product(product)
        // .order(order)
        // .orderPrice(25000)
        // .orderAmount((int) (Math.random() * 15) + 1)
        // .build();
        // orderItemRepository.save(orderItem);
      });
  }

  @Test
  public void insertStoreTest() {
    LongStream
      .rangeClosed(1, 200)
      .forEach(i -> {
        Store store = storeRepository.findById(i).get();

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
      .rangeClosed(64, 127)
      .forEach(i -> {
        Store store = Store.builder().storeId(i).build();
        LongStream
          .rangeClosed(1, 10)
          .forEach(j -> {
            Product product = Product
              .builder()
              .pName("상품" + i)
              .price((int) ((Math.random() * 1000) + i))
              .amount((int) ((Math.random() * 20) + i))
              .store(store)
              .build();
            productRepository.save(product);

            int count = (int) (Math.random() * 5) + 1;

            for (int k = 0; k < count; k++) {
              ProductImg productImg = ProductImg
                .builder()
                .uuid(UUID.randomUUID().toString())
                .path(null)
                .imgName("img" + k + ".jpg")
                .product(product)
                .build();
              productImgRepository.save(productImg);
            }
          });
      });
  }

  @Test
  public void insertProductTestssss() {
    // 임시 사용
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

        for (int k = 0; k < count; k++) {
          ProductImg productImg = ProductImg
            .builder()
            .uuid(UUID.randomUUID().toString())
            .path(null)
            .imgName("img" + k + ".jpg")
            .product(product)
            .build();
          productImgRepository.save(productImg);
        }
      });
  }

  @Test
  public void insertReviewTest() {
    LongStream
      .rangeClosed(201, 210)
      .forEach(i -> {
        Member member = Member
          .builder()
          .memberEmail("member1@naver.com")
          .build();

        Product product = Product.builder().productId(193L).build();

        Review review = Review
          .builder()
          .text("이 매장에 대한 리뷰는.....")
          .grade((int) (Math.random() * 5) + 1)
          .product(product)
          .member(member)
          .build();
        reviewRepository.save(review);
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

  @Transactional
  @Test
  public void insertProductTest1() {
    Product product = new Product();
    product.setAmount(10);
    product.setPName("테스트상품");
    product.setPrice(1000);
    product.setStore(Store.builder().storeId(10L).build());

    productRepository.save(product);

    int count = (int) (Math.random() * 5) + 1;

    for (int j = 0; j < count; j++) {
      ProductImg productImg = ProductImg
        .builder()
        .uuid(UUID.randomUUID().toString())
        .path(null)
        .imgName("img" + j + ".jpg")
        .product(product)
        .build();
      productImgRepository.save(productImg);
    }
  }

  @Test
  public void product200test() {
    // 임시 사용
    LongStream
      .rangeClosed(1, 100)
      .forEach(i -> {
        Product product = Product
          .builder()
          .pName("재고" + i)
          .price(5000)
          .amount(20)
          .store(Store.builder().storeId(198L).build())
          .build();
        productRepository.save(product);
      });
  }

  @Test
  public void findAllOrder() {
    Member member = memberRepository.findById("ksb1234@naver.com").get();
    System.out.println(orderRepository.findAllOrderByMember(member));
  }
}
