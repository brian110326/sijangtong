package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
