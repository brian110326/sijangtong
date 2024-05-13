package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@SpringBootTest
public class SijangtongServiceTest {

    @Autowired
    private StoreImgRepository storeImgRepository;

    @Autowired
    private ProductImgRepository productImgRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void storeList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();

        Page<Object[]> list = storeImgRepository.getTotalList(requestDto.getPageable(Sort.by("storeId").descending()));
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void getStoreRow() {
        List<Object[]> list = storeImgRepository.getStoreRow(282L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void productList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> list = productImgRepository.getProductList(requestDto.getPageable(Sort.by("productId")), 44L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void getProductRow() {
        List<Object[]> list = productImgRepository.getProductRow(7L);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    @Commit
    @Transactional
    public void deleteStoreTest() {
        Store store = storeRepository.findById(200L).get();
        Product product = productRepository.findByStore(store).get(0);
        Review review = reviewRepository.findByStore(store).get(0);
        Order order = orderRepository.findByReview(review);

        // System.out.println(store);
        // System.out.println(product);
        // System.out.println(review);
        // System.out.println(order);

        reviewRepository.deleteByStore(store);
        storeImgRepository.deleteByStore(store);

        orderItemRepository.deleteByProduct(product);
        productImgRepository.deleteByProduct(product);

        productRepository.deleteByStore(store);
        orderRepository.delete(order);

        storeRepository.delete(store);
    }

}
