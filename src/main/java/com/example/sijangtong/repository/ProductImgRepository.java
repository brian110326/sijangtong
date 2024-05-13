package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.repository.total.ProductImgProductRepository;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long>, ProductImgProductRepository {

}
