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
        Optional<List<Order>> ordersOptional = orderRepository.findAllOrderByMember(member);

        int list_check = 0;
        int status_Cnt = 0;
        // 오더 존재 여부 체크 O
        if (ordersOptional.isPresent()) {
            // 오더 리스트 불러오기
            List<Order> orders = ordersOptional.get();
            for (Order order : orders) {
                // 오더의 상태가 배달 중일 경우
                if (order.getOrderSatetus() != null) {
                    status_Cnt += 1;
                    // 체크 후 진행
                    continue;
                } else {
                    // 배달중이 아닐경우 storId 가 같은 값인지 체크
                    if (order.getStore().getStoreId() != product.getStore().getStoreId()) {
                        // 아니면 -1 값 전송
                        return -1L;
                    }
                    // 오더에 등록된 아이템 리스트 불러오기
                    List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getOrderId());
                    for (OrderItem orderItem : orderItems) {
                        // 만일 오더 아이템에 이미 담긴 상품을 주문시 해당 상품 업데이트후 리턴
                        if (orderItem.getProduct() == product) {
                            orderItem.setOrderAmount(amount);
                            orderItem.setOrderPrice(amount + product.getPrice());
                            orderItemRepository.save(orderItem);
                            return order.getOrderId();
                        } else {
                            // 다른 상품인지 체크
                            list_check += 1;
                            continue;
                        }
                    }
                    // 체크한 값이랑 리스트 전체 값이랑 같을시 새로운 아이템 생성
                    if (list_check == orderItems.size()) {
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
            }
            // 전부 배달 중인 주문일 경우 신규 오더 생성
            if (status_Cnt == orders.size()) {
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
            return 0L;
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

        Optional<List<Order>> ordersOptional = orderRepository.findAllOrderByMember(member.get());
        // log.info(order.get().getOrderSatetus());

        if (ordersOptional.isPresent()) {
            List<Order> orders = ordersOptional.get();
            for (Order order : orders) {
                if (order.getOrderSatetus() == null) {
                    List<OrderItem> items = orderItemRepository.findByOrderId(order.getOrderId());
                    for (OrderItem orderItem : items) {
                        orderItemDtos.add(entityToDto(orderItem));
                    }
                }
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
        // 회원을 찾습니다
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);

        if (!member.isPresent()) {
            return Collections.emptyList();
        }

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        // 회원에 대한 주문을 찾습니다

        Optional<List<Order>> ordersOptional = orderRepository.findAllOrderByMember(member.get());
        // log.info(order.get().getOrderSatetus());

        if (ordersOptional.isPresent()) {
            List<Order> orders = ordersOptional.get();
            for (Order order : orders) {
                if (order.getOrderSatetus() != null) {
                    List<OrderItem> items = orderItemRepository.findByOrderId(order.getOrderId());
                    for (OrderItem orderItem : items) {
                        orderItemDtos.add(entityToDto(orderItem));
                    }
                }
            }

            return orderItemDtos;
        } else {
            return new ArrayList<>();
        }
    }

}
