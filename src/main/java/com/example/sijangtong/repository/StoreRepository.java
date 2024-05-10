package com.example.sijangtong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s")
    Page<Object> getListPage(Pageable pageable);
}
