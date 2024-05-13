package com.example.sijangtong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;

public interface StoreImgRepository extends JpaRepository<StoreImg, Long>, StoreImgStoreRepository {

}
