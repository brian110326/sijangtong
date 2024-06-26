package com.example.sijangtong.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductImgProductRepository {
    Page<Object[]> getProductList(String type, String keyword, Pageable pageable, Long storeId);

    List<Object[]> getProductRow(Long productId);
}
