package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Query("delete from OrderItem oi where oi.product = :product")
    void deleteByProduct(Product product);

    List<OrderItem> findByProduct(Product product);

    @Modifying
    @Query("delete from OrderItem oi where oi.order = :order")
    void deleteByOrder(Order order);

    @Modifying
    @Query("update OrderItem oi set oi.orderAmount = :orderAmount where oi.id = :id")
    void updateAmount(int orderAmount, Long id);

}
