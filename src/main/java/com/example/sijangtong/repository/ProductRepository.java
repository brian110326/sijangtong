package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // @Query("select p from Product p where p.store = :store")
    // Optional<Product> findByStore(Store store);

    List<Product> findByStore(Store store);

    @Modifying
    @Query("delete from Product p where p.store = :store")
    void deleteByStore(Store store);
}
