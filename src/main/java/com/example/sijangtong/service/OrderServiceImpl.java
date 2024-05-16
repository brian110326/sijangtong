package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.OrderDto;
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
import com.example.sijangtong.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public PageResultDto<OrderDto, Object[]> getOrderList(PageRequestDto pageRequestDto, Long storeId) {
        Page<Object[]> result = orderRepository.getOrderList(pageRequestDto.getPageable(Sort.by("orderId")), storeId);

        Function<Object[], OrderDto> fn = (en -> entityToDto((Order) en[0],
                (OrderItem) en[1], (Rider) en[2], (Member) en[3], (Product) en[4], (Store) en[5]));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);

        return orderId;
    }

    @Override
    public Long updateOrder(OrderDto orderDto) {
        Order updatedOrder = orderRepository.save(dtoToEntity(orderDto));

        return updatedOrder.getOrderId();
    }

    @Override
    public Long createOrder(OrderDto orderDto) {
        Order newOrder = dtoToEntity(orderDto);
        orderRepository.save(newOrder);

        return newOrder.getOrderId();
    }

}
