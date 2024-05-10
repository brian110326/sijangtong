package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.store = :store")
    List<Product> findByStore(Store store);
}
