package com.example.sijangtong.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.sijangtong.entity.Store;

public interface StoreImgStoreRepository {


    Page<Object[]> getTotalList(String type, String keyword, Pageable pageable);

    List<Object[]> getStoreRow(Long storeId);

}
