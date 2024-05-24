package com.example.sijangtong.service;

import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
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

    @Override
    public Long createOrderItem(int amount, Product product) {

        OrderItem orderItem = OrderItem.builder().build();

        return 0L;
    }

}
