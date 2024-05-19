package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.constant.OrderPayment;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.orderTotal.OrderOrderItemMemberRiderProductRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderOrderItemMemberRiderProductRepository {

    List<Order> findByStore(Store store);

    @Modifying
    @Query("delete from Order o where o.store = :store")
    void deleteByStore(Store store);

    @Modifying
    @Query("delete from Order o where o.member = :member")
    void deleteByMember(Member member);

    List<Order> findByMember(Member member);

    @Modifying
    @Query("update Order o set o.orderPayment = :orderPayment where o.orderId = :orderId")
    void updatePayment(OrderPayment orderPayment, Long orderId);

}
