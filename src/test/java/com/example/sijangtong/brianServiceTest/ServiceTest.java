package com.example.sijangtong.brianServiceTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.repository.MemberRepository;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import com.example.sijangtong.repository.RiderRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private StoreImgRepository storeImgRepository;

    @Autowired
    private ProductImgRepository productImgRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Test
    @Transactional
    @Commit
    public void memberDeleteTest() {
        Member member = Member.builder().memberEmail("member98@naver.com").build();
        List<Order> orders = orderRepository.findByMember(member);

        reviewRepository.deleteByMember(member);

        orders.forEach(order -> {
            orderItemRepository.deleteByOrder(order);
            orderRepository.delete(order);
        });

        memberRepository.delete(member);
    }

}
