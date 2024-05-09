package com.example.sijangtong.repository;

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
    public void insertStoreTest() {
        LongStream.rangeClosed(1, 200).forEach(i -> {
            Store store = Store.builder()
                    .storeCategory(StoreCategory.SEAFOOD)
                    .build();

            storeRepository.save(store);
        });
    }

}
