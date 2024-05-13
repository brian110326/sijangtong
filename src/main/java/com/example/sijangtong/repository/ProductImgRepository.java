package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.repository.total.ProductImgProductRepository;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long>, ProductImgProductRepository {

    @Modifying
    @Query("delete from ProductImg pi where pi.product = :product")
    void deleteByProduct(Product product);
}
