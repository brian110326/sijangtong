package com.example.sijangtong.dwTest;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Store;
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
public class insertTest {

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
    public void insertAdminTest() {
        Member member = Member.builder()
                .memberEmail("ADMIN@naver.com")
                .memberNickname("어드민")
                .memberAddress("종로")
                .memberPwd(passwordEncoder.encode("1111"))
                .memberRole(MemberRole.ADMIN)
                .build();
        memberRepository.save(member);
    }

    @Test
    public void insertProductTest() {

        Store store = Store.builder().storeId(200L).build();
        LongStream
                .rangeClosed(201, 211)
                .forEach(i -> {
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
    };
}
