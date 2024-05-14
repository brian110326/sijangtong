package com.example.sijangtong.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
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

    // @Test
    // public void storeList() {
    // PageRequestDto requestDto =
    // PageRequestDto.builder().size(10).page(1).build();

    // Page<Object[]> list =
    // storeImgRepository.getTotalList(requestDto.getPageable(Sort.by("storeId").descending()));
    // for (Object[] objects : list) {
    // System.out.println(Arrays.toString(objects));
    // }

    // }

    // @Test
    // public void getStoreListByCategory() {
    // PageRequestDto requestDto =
    // PageRequestDto.builder().size(10).page(1).build();
    // Page<Object[]> list =
    // storeImgRepository.getTotalListByCategory(requestDto.getPageable(Sort.by("storeId")),
    // StoreCategory.SEAFOOD);

    // for (Object[] objects : list) {
    // System.out.println(Arrays.toString(objects));
    // }

    // }

    // @Test
    // public void getStoreListByAddress() {
    // PageRequestDto requestDto =
    // PageRequestDto.builder().size(10).page(1).build();
    // Page<Object[]> list =
    // storeImgRepository.getTotalListByAddress(requestDto.getPageable(Sort.by("storeId")),
    // "종로");

    // for (Object[] objects : list) {
    // System.out.println(Arrays.toString(objects));
    // }
    // }

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
    public void reviewList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> list = reviewRepository.getReviewList(requestDto.getPageable(Sort.by("reviewId")), 198L);

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

    @Test
    @Commit
    @Transactional
    public void deleteProduct() {
        Product product = productRepository.findById(199L).get();
        OrderItem orderItem = orderItemRepository.findByProduct(product);
        // System.out.println(product);
        // System.out.println(orderItem);

        productImgRepository.deleteByProduct(product);

        // product 삭제 시 orderItem안 product항목만 null
        orderItem.setProduct(null);

        productRepository.delete(product);
    }

    @Test
    public void getReviewListByStore() {
        // store에 대한 reviewlist 보여주기
        Store store = Store.builder().storeId(199L).build();

        List<Review> list = reviewRepository.findByStore(store);

        list.forEach(review -> System.out.println(review));
    }

    @Test
    public void deleteReview() {
        reviewRepository.deleteById(199L);
    }

}
