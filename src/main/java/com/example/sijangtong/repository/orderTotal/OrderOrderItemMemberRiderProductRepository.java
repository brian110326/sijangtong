package com.example.sijangtong.repository.orderTotal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderOrderItemMemberRiderProductRepository {
    Page<Object[]> getOrderList(Pageable pageable, Long storeId);
}
