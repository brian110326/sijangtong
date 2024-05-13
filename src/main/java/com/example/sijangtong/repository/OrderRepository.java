package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("delete from Order o where o.review = :review")
    void deleteByReview(Review review);

    Order findByReview(Review review);

}
