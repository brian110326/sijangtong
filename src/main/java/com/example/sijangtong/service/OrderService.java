package com.example.sijangtong.service;

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
import java.util.List;
import java.util.stream.Collectors;

public interface OrderService {
  // 주문 리스트 보여주기
  PageResultDto<OrderDto, Object[]> getOrderList(
      PageRequestDto pageRequestDto,
      Long storeId);

  // 주문 취소(고객이)
  Long removeOrder(Long orderId);

  // 주문 자체 취소가 아닌 주문 안에 주문아이템만 삭제
  Long removeOrderItem(Long orderItemId);

  // 주문의 결제방식을 수정
  Long updateOrder(OrderDto orderDto);

  // 주문아이템의 주문 수량 변경
  void updateOrderAmount(OrderItemDto orderItemDto);

  // 주문하기
  Long createOrder(OrderDto orderDto);

  public default OrderDto entityToDto(
      Order order,
      List<OrderItem> orderItems,
      Rider rider,
      Member member,
      List<Product> products,
      Store store) {
    OrderDto orderDto = OrderDto
        .builder()
        .orderId(order.getOrderId())
        .orderAddress(order.getOrderAddress())
        .riderOrdercancel(order.getRiderOrdercancel())
        .orderPayment(order.getOrderPayment())
        .memberEmail(order.getMember().getMemberEmail())
        .storeId(order.getStore().getStoreId())
        .rider(order.getRider())
        .createdDate(order.getCreatedDate())
        .lastModifiedDate(order.getLastModifiedDate())
        .build();

    List<ProductDto> productDtos = products
        .stream()
        .map(product -> {
          return ProductDto
              .builder()
              .productId(product.getProductId())
              .pName(product.getPName())
              .price(product.getPrice())
              .amount(product.getAmount())
              .storeId(product.getStore().getStoreId())
              .build();
        })
        .collect(Collectors.toList());

    List<OrderItemDto> orderItemDtos = orderItems
        .stream()
        .map(orderItem -> {
          return OrderItemDto
              .builder()
              .id(orderItem.getId())
              .productDtos(productDtos)
              .orderId(orderItem.getOrder().getOrderId())
              // orderPrice는 주문수량 * product의 원가격
              .orderPrice(orderItem.getOrderPrice())
              .orderAmount(orderItem.getOrderAmount())
              .build();
        })
        .collect(Collectors.toList());

    orderDto.setOrderItemDtos(orderItemDtos);

    // 하나의 주문에 여러개의 orderItem, orderItem 안에 여러개의 product존재

    return orderDto;
  }

  public default Order dtoToEntity(OrderDto orderDto) {
    Member member = Member
        .builder()
        .memberEmail(orderDto.getMemberEmail())
        .build();
    Store store = Store.builder().storeId(orderDto.getStoreId()).build();

    Order order = Order
        .builder()
        .orderId(orderDto.getOrderId())
        .orderAddress(orderDto.getOrderAddress())
        .orderPayment(orderDto.getOrderPayment())
        .member(member)
        .store(store)
        .rider(orderDto.getRider())
        .riderOrdercancel(orderDto.getRiderOrdercancel())
        .build();

    order.setCreatedDate(orderDto.getCreatedDate());
    order.setLastModifiedDate(orderDto.getLastModifiedDate());

    return order;
  }
}
