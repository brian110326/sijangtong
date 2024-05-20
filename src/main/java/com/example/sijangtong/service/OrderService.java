package com.example.sijangtong.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;

public interface OrderService {
    PageResultDto<OrderDto, Object[]> getOrderList(PageRequestDto pageRequestDto, Long storeId);

    Long removeOrder(Long orderId);

    Long removeOrderItem(Long orderItemId);

    Long updateOrder(OrderDto orderDto);

    void updateOrderAmount(OrderItemDto orderItemDto);

    Long createOrder(OrderDto orderDto);

    public default OrderDto entityToDto(Order order, List<OrderItem> orderItems, Rider rider, Member member,
            List<Product> products,
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

        List<ProductDto> productDtos = products.stream().map(product -> {
            return ProductDto.builder().productId(product.getProductId()).pName(product.getPName())
                    .price(product.getPrice()).amount(product.getAmount()).storeId(product.getStore().getStoreId())
                    .build();
        }).collect(Collectors.toList());

        List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> {
            return OrderItemDto.builder().id(orderItem.getId()).productDtos(productDtos)
                    .orderId(orderItem.getOrder().getOrderId())
                    // orderPrice는 주문수량 * product의 원가격
                    .orderPrice(orderItem.getOrderPrice())
                    .orderAmount(orderItem.getOrderAmount()).build();
        }).collect(Collectors.toList());

        orderDto.setOrderItemDtos(orderItemDtos);

        // 하나의 주문에 여러개의 orderItem, orderItem 안에 여러개의 product존재

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
