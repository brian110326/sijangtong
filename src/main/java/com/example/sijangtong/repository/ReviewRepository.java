package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("delete from Review r where r.store = :store")
    void deleteByStore(Store store);

    @Query("select r from Review r where r.store = :store")
    List<Review> findByStore(Store store);

}
