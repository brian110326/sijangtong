package com.example.sijangtong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;
import java.util.List;
import com.example.sijangtong.constant.StoreCategory;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
