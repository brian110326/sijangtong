package com.example.sijangtong.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.entity.Store;

public interface StoreImgStoreRepository {

    Page<Object[]> getTotalList(Pageable pageable);

    Page<Object[]> getTotalListByCategory(Pageable pageable, StoreCategory storeCategory);

    Page<Object[]> getTotalListByAddress(Pageable pageable, String storeAddress);

    List<Object[]> getStoreRow(Long storeId);

}
