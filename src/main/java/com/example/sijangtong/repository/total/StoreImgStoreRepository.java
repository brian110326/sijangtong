package com.example.sijangtong.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.sijangtong.entity.Store;

public interface StoreImgStoreRepository {
<<<<<<< HEAD
    
    Page<Object[]> getTotalList( Pageable pageable);
=======

    Page<Object[]> getTotalList(String type, String keyword, Pageable pageable);
>>>>>>> 50a9ce7d418bc8fe3264f6aacd6ab493666f6d71

    List<Object[]> getStoreRow(Long storeId);

}
