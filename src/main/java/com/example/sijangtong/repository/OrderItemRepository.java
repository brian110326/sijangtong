package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
