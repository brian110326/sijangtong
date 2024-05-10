package com.example.sijangtong.repository;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;

import groovy.transform.AutoImplement;

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

    @Test
    public void insertMemberTest() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Member member = Member.builder()
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

        LongStream.rangeClosed(1, 200).forEach(i -> {
            Member member = Member.builder().memberEmail("member" + i + "@naver.com").build();
            Order order = Order.builder()
                    .orderAddress("강동구")
                    .orderPayment(OrderPayment.CASH)
                    .member(member)
                    .build();

            orderRepository.save(order);
        });
    }

    @Test
    public void insertOrderItemTest() {

        LongStream.rangeClosed(1, 200).forEach(i -> {
            Product product = Product.builder().productId(i).build();
            Order order = Order.builder().orderId(i).build();

            OrderItem orderItem = OrderItem.builder()
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
        LongStream.rangeClosed(1, 200).forEach(i -> {
            Store store = Store.builder()
                    .storeCategory(StoreCategory.SEAFOOD)
                    .storeTel("010-1111-1" + i)
                    .openTime("6 시에 오픈")
                    .closeTime("10시에 마감")
                    .storeAddress("종로")
                    .storeName("이것은 가계요" + i)
                    .storeDetail("이 가계는....")
                    .build();

            storeRepository.save(store);
        });
    }

    @Test
    public void insertProductTest() {

        LongStream.rangeClosed(1, 200).forEach(i -> {
            Store store = Store.builder().storeId(i).build();
            Order order = Order.builder().orderId(i).build();

            Product product = Product.builder()
                    .pName("재고" + i)
                    .price(5000)
                    .amount(20)
                    .store(store)
                    .order(order)
                    .build();

            productRepository.save(product);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                ProductImg productImg = ProductImg.builder()
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
    public void insertReviewTest() {

        LongStream.rangeClosed(1, 200).forEach(i -> {

            Store store = Store.builder().storeId(i).build();
            Member member = Member.builder().memberEmail("member" + i + "@naver.com").build();

            Review review = Review.builder()
                    .text("이 매장에 대한 리뷰는.....")
                    .grade((int) (Math.random() * 5) + 1)
                    .store(store)
                    .member(member)
                    .build();
            reviewRepository.save(review);
        });
    }

}