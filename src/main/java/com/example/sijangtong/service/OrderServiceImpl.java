package com.example.sijangtong.service;

import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.RiderRepository;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final OrderItemRepository orderItemRepository;

  private final RiderRepository riderRepository;

  @Override
  public PageResultDto<OrderDto, Object[]> getOrderList(
      PageRequestDto pageRequestDto,
      Long storeId) {

    Page<Object[]> result = orderRepository.getOrderList(
        pageRequestDto.getPageable(Sort.by("orderId")),
        storeId);

    Function<Object[], OrderDto> fn = (en -> entityToDto((Order) en[0],
        (List<OrderItem>) Arrays.asList((OrderItem) en[1])));

    return new PageResultDto<>(result, fn);
  }

  // order 자체를 삭제할 땐 orderItem 모두 삭제(O)
  @Override
  public Long removeOrder(Long orderId) {
    Order order = orderRepository.findById(orderId).get();

    orderItemRepository.deleteByOrder(order);

    orderRepository.deleteById(orderId);

    return orderId;
  }

  // order 안에 특정 orderItem만 삭제하는 경우
  @Override
  public Long removeOrderItem(Long orderItemId) {
    orderItemRepository.deleteById(orderItemId);

    return orderItemId;
  }

  // Order를 수정 => 결제방법을 수정
  @Override
  public Long updateOrder(OrderDto orderDto) {
    orderRepository.updatePayment(
        orderDto.getOrderPayment(),
        orderDto.getOrderId());

    return orderDto.getOrderId();
  }

  // orderItem 안 product의 개수를 수정
  // 개수 수정시 주문 가격이 자동으로 바뀌는 것은 javascript로 하기
  // orderItem의 orderPrice = orderAmount * product의 Price
  @Override
  public void updateOrderAmount(OrderItemDto orderItemDto) {
    orderItemRepository.updateAmount(
        orderItemDto.getOrderAmount(),
        orderItemDto.getId());
  }

  // 주문 => 주소, 결제방식, 누가, 어떤 store에서, rider는 자동배정
  @Override
  public Long createOrder(OrderDto orderDto) {
    // 주문 시 waiting 중이면서 riderId가 최소인 rider 배정
    Rider rider = riderRepository.getRider();

    orderDto.setRider(rider);

    // 문제 생긴다면 set함수 이용해보기
    Order newOrder = dtoToEntity(orderDto);
    orderRepository.save(newOrder);

    return newOrder.getOrderId();
  }
}
