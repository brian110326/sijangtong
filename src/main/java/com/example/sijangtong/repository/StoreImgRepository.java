package com.example.sijangtong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;

public interface StoreImgRepository extends JpaRepository<StoreImg, Long>, StoreImgStoreRepository {

    @Modifying
    @Query("delete from StoreImg si where si.store = :store")
    void deleteByStore(Store store);
}
