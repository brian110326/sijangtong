package com.example.sijangtong.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;

public interface OrderItemService {

    Long createOrderItem(int amount, Long productId, String memberEmail, Long storeId);

    List<OrderItemDto> getMemberOrderItems(String memberEmail);

    void deleteOrderItem(Long orderItemId);

    public default OrderItemDto entityToDto(OrderItem orderItem) {

        OrderItemDto orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getOrderId())
                .orderPrice(orderItem.getOrderPrice())
                .orderAmount(orderItem.getOrderAmount())
                .productId(orderItem.getProduct().getProductId())
                .pName(orderItem.getProduct().getPName())
                .price(orderItem.getProduct().getPrice())
                .storId(orderItem.getProduct().getStore().getStoreId())
                .build();

        return orderItemDto;
    }

    public default OrderItem dtoToEntity(OrderItemDto orderItemDto) {

        Order order = Order
                .builder()
                .orderId(orderItemDto.getOrderId())
                .build();

        Product product = Product.builder().productId(orderItemDto.getProductId()).build();

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .id(orderItemDto.getId())
                .orderAmount(orderItemDto.getOrderAmount())
                .orderPrice(orderItemDto.getOrderPrice())
                .build();

        return orderItem;
    }
}
