package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Query("delete from OrderItem oi where oi.product = :product")
    void deleteByProduct(Product product);
}
