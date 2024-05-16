package com.example.sijangtong.service;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;

public interface OrderService {
    PageResultDto<OrderDto, Object[]> getOrderList(PageRequestDto pageRequestDto, Long storeId);

    Long removeOrder(Long orderId);

    Long updateOrder(OrderDto orderDto);

    Long createOrder(OrderDto orderDto);

    public default OrderDto entityToDto(Order order, OrderItem orderItem, Rider rider, Member member, Product product,
            Store store) {
        OrderDto orderDto = OrderDto.builder().orderId(order.getOrderId())
                .orderAddress(order.getOrderAddress())
                .orderPayment(order.getOrderPayment())
                .memberEmail(order.getMember().getMemberEmail())
                .storeId(order.getStore().getStoreId())
                .rider(order.getRider())
                .createdDate(order.getCreatedDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .build();

        return orderDto;
    }

    public default Order dtoToEntity(OrderDto orderDto) {
        Member member = Member.builder().memberEmail(orderDto.getMemberEmail()).build();
        Store store = Store.builder().storeId(orderDto.getStoreId()).build();

        Order order = Order.builder().orderId(orderDto.getOrderId()).orderAddress(orderDto.getOrderAddress())
                .orderPayment(orderDto.getOrderPayment()).member(member).store(store).rider(orderDto.getRider())
                .build();

        order.setCreatedDate(orderDto.getCreatedDate());
        order.setLastModifiedDate(orderDto.getLastModifiedDate());

        return order;
    }
}
