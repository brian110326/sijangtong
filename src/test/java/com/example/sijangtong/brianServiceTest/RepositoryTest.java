package com.example.sijangtong.brianServiceTest;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.MemberRepository;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import com.example.sijangtong.repository.RiderRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

@SpringBootTest
public class RepositoryTest {
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
                .rangeClosed(1, 200)
                .forEach(i -> {
                    Member member = Member
                            .builder()
                            .memberEmail("member" + i + "@naver.com")
                            .memberNickname("이것은 닉네임..")
                            .memberAddress("종로")
                            .memberPwd(passwordEncoder.encode("1111"))
                            .memberRole(MemberRole.MEMBER)
                            .build();

                    memberRepository.save(member);
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
                });

    }

    @Test
    public void insertStoreImgTest() {
        LongStream
                .rangeClosed(1, 200)
                .forEach(i -> {
                    Store store = Store.builder().storeId(i).build();
                    StoreImg storeImg = StoreImg
                            .builder()
                            .stUuid(UUID.randomUUID().toString())
                            .stPath(null)
                            .stImgName("stImg" + i + ".jpg")
                            .store(store)
                            .build();

                    storeImgRepository.save(storeImg);
                });

    }

    @Test
    public void insertProductTest() {
        // storeId가 200인 Store 객체 생성

        LongStream.rangeClosed(201, 300).forEach(i -> {
            Store store = Store.builder().storeId(200L).build();
            Product product = Product.builder()
                    .pName("재고" + i)
                    .price(5000)
                    .amount(20)
                    .store(store)
                    .build();
            productRepository.save(product);
        });
    }
}