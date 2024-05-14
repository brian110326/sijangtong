package com.example.sijangtong.repository.reviewTotal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewStoreProductOrderRepository {
    Page<Object[]> getReviewList(Pageable pageable, Long storeId);
}
