package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.reviewTotal.ReviewProductRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewProductRepository {

    @Modifying
    @Query("delete from Review r where r.product = :product")
    void deleteByProduct(Product product);

    @Query("select r from Review r where r.product = :product")
    List<Review> findByProduct(Product product);

    @Modifying
    @Query("update Review r set r.text = :text, r.grade = :grade where r.reviewId = :reviewId ")
    void updateReview(String text, int grade, Long reviewId);

}
