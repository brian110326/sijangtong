package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
