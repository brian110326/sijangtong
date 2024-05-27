package com.example.sijangtong.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.MemberRepository;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    @Override
    public Long createOrderItem(int amount, Long productId, String memberEmail, Long storeId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        Member member = memberRepository.findByMemberEmail(memberEmail).orElse(null);
        if (member == null) {
            throw new IllegalArgumentException("Member not found with email: " + memberEmail);
        }

        Order order = orderRepository.findByMember(member).orElse(null);
        if (order == null) {
            order = Order.builder()
                    .member(member)
                    .orderAddress(member.getMemberAddress())
                    .store(Store.builder().storeId(storeId).build())
                    .build();
            orderRepository.save(order);
        }

        OrderItem orderItem = OrderItem.builder()
                .order(order) // 주문에 대한 참조 설정
                .product(product)
                .orderPrice(amount * product.getPrice())
                .orderAmount(amount)
                .build();
        orderItemRepository.save(orderItem);

        return order.getOrderId();
    }

    // 오더 아이템 리스트 보여주기
    @Override
    public List<OrderItem> getMemberOrderItems(String memberEmail) {
        // 회원을 찾습니다
        Member member = memberRepository.findByMemberEmail(memberEmail).get();

        // 회원이 존재하는지 확인합니다
        if (member == null) {
            // 회원이 존재하지 않는 경우 처리합니다
            return Collections.emptyList(); // 또는 예외를 throw할 수 있습니다
        }

        // 회원에 대한 주문을 찾습니다
        // Order orders = orderRepository.findByMember(member);

        // 각 주문에 대해 주문 항목을 가져옵니다

        // List<OrderItem> items =
        // orderItemRepository.findByOrderId(orders.getOrderId());

        return null;
    }

}
