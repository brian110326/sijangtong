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

        int list_check = 0;
        // 오더 존재 여부 체크 O
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            // order 안에 상점 아이디와 주문한 상품의 상점 아이디가 다를시 return 0
            if (order.getStore().getStoreId() != product.getStore().getStoreId()) {
                return -1L;
            }
            // 해당하는 오더의 오더 아이템 리스트 들 중 중복 되는 상품 체크
            List<OrderItem> orderitems = orderItemRepository.findByOrderId(order.getOrderId());
            for (OrderItem orderItem : orderitems) {
                // 중복되는 상품 존재시 해당 상품 업데이트
                if (orderItem.getProduct() == product) {
                    orderItem.setOrderAmount(amount);
                    orderItem.setOrderPrice(amount * product.getPrice());
                    orderItemRepository.save(orderItem);
                } else {
                    // 중복되지 않는 상품 카운트
                    list_check += 1;
                    continue;
                }
            }
            // 만일 리스트중 겹치는 상품이 없으면 신규 orederitem 생성
            if (list_check == orderitems.size()) {
                OrderItem orderItem = OrderItem.builder()
                        .orderAmount(amount)
                        .product(product)
                        .orderPrice(amount * product.getPrice())
                        .order(order)
                        .build();
                orderItemRepository.save(orderItem);

                return order.getOrderId();
            }
            // 오더 값 리턴
            return order.getOrderId();
        }
        // 오더 존재 x
        else {
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
        Optional<Order> order = orderRepository.findByMember(member.get());
        log.info(order.get().getOrderSatetus());
        if (order.isPresent() && order.get().getOrderSatetus() == null) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.get().getOrderId());
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

    @Override
    public List<OrderItemDto> getDeliveringOrderItems(String memberEmail) {
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);

        if (!member.isPresent()) {
            return Collections.emptyList();
        }

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        // 회원에 대한 주문을 찾습니다
        Optional<Order> order = orderRepository.findByMember(member.get());
        log.info(order.get().getOrderSatetus());
        if (order.isPresent() && order.get().getOrderSatetus() != null) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.get().getOrderId());
            for (OrderItem orderItem : items) {
                orderItemDtos.add(entityToDto(orderItem));
            }

            return orderItemDtos;
        } else {
            return new ArrayList<>();
        }
    }

}
