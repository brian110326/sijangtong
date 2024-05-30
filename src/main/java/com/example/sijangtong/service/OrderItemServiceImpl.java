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
    public Long createOrderItem(int amount, Long productId, String memberEnail, Long storeId) {
        Product product = productRepository.findById(productId).get();
        // Member member = Member.builder().memberEmail(memberEnail).build();
        Member member = memberRepository.findByMemberEmail(memberEnail).get();
        Optional<Order> orderOptional = orderRepository.findByMember(member);

        // 오더 존재 여부 체크 O
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            // 이미 장바구니에 있는 상품을 축가했을경우 해당하는 수량과 가격으로 수정
            if (orderItemRepository.findByProduct(product).get().getOrder().getOrderId() == order.getOrderId()) {
                OrderItem orderItem = orderItemRepository.findByProduct(product).get();
                orderItem.setOrderAmount(amount);
                orderItem.setOrderPrice(amount * product.getPrice());
                orderItemRepository.save(orderItem);
                return orderItem.getOrder().getOrderId();
            }
            // 장바구니에 없는 신규일 경우
            else {
                OrderItem orderItem = OrderItem.builder()
                        .order(order)
                        .orderAmount(amount)
                        .orderPrice(amount * product.getPrice())
                        .product(product)
                        .build();
                orderItemRepository.save(orderItem);
                return orderItem.getOrder().getOrderId();
            }
            // 오더 존재 x
        } else {
            Order order = Order.builder()
                    .member(member)
                    .orderAddress(member.getMemberAddress())
                    .store(Store.builder().storeId(storeId).build())
                    .build();
            orderRepository.save(order);
            OrderItem orderItem = OrderItem.builder()
                    .orderAmount(amount)
                    .product(product)
                    .orderPrice(amount * product.getPrice())
                    .order(order)
                    .build();
            orderItemRepository.save(orderItem);

            return order.getOrderId();
        }

    }

    // 오더 아이템 리스트 보여주기
    @Override
    public List<OrderItemDto> getMemberOrderItems(String memberEmail) {
        // 회원을 찾습니다
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);

        if (!member.isPresent()) {
            return Collections.emptyList();
        }

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        // 회원에 대한 주문을 찾습니다
        Optional<Order> orders = orderRepository.findByMember(member.get());
        if (orders.isPresent()) {
            List<OrderItem> items = orderItemRepository.findByOrderId(orders.get().getOrderId());
            for (OrderItem orderItem : items) {
                orderItemDtos.add(entityToDto(orderItem));
            }

            return orderItemDtos;
        } else {
            return new ArrayList<>();
        }

        // 각 주문에 대해 주문 항목을 가져옵니다

    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

}
