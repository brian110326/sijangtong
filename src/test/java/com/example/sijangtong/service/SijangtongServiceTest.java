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

    @Test
    public void storeList() {

        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(3).build();

        Page<Object[]> list = storeImgRepository.getTotalList(requestDto.getPageable(Sort.by("storeId").descending()));
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        System.out.println("number " + list.getNumber());
        System.out.println("total " + list.getTotalPages());
        System.out.println("TotalElements " + list.getTotalElements());
        System.out.println("size " + list.getSize());
        // System.out.println("getPageNumber " + list.getPageable().getPageNumber());
        System.out.println("getOffset " + list.getPageable().getOffset());

    }

    @Test
    public void getStoreListByCategory() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> list = storeImgRepository.getTotalListByCategory(requestDto.getPageable(Sort.by("storeId")),
                StoreCategory.SEAFOOD);

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void getStoreListByAddress() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> list = storeImgRepository.getTotalListByAddress(requestDto.getPageable(Sort.by("storeId")),
                "종로");

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        System.out.println("number" + list.getNumber());
        System.out.println("total" + list.getTotalPages());
        System.out.println("TotalElements" + list.getTotalElements());
    }

    @Test
    public void getStoreRow() {
        List<Object[]> list = storeImgRepository.getStoreRow(200L);

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
        Page<Object[]> list = reviewRepository.getReviewList(requestDto.getPageable(Sort.by("reviewId")), 1L);

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
        Store store = storeRepository.findById(199L).get();
        Product product = productRepository.findByStore(store).get(0);
        Review review = reviewRepository.findByProduct(product).get(0);
        OrderItem orderItem = orderItemRepository.findByProduct(product).get(0);

        reviewRepository.deleteByProduct(product);
        storeImgRepository.deleteByStore(store);

        orderItemRepository.deleteByProduct(product);
        productImgRepository.deleteByProduct(product);

        productRepository.deleteByStore(store);
        orderRepository.deleteByStore(store);

        storeRepository.delete(store);
    }

    @Test
    @Commit
    @Transactional
    public void deleteProduct() {
        Product product = productRepository.findById(198L).get();
        List<OrderItem> orderItems = orderItemRepository.findByProduct(product);

        productImgRepository.deleteByProduct(product);

        // product 삭제 시 orderItem안 product항목만 null로 설정
        orderItems.forEach(orderItem -> orderItem.setProduct(null));
        reviewRepository.deleteByProduct(product);

        productRepository.delete(product);
    }

    @Test
    public void getReviewListByProduct() {
        // store에 대한 reviewlist 보여주기
        Product product = Product.builder().productId(1L).build();

        List<Review> list = reviewRepository.findByProduct(product);

        list.forEach(review -> System.out.println(review));
    }

    @Test
    public void deleteReview() {
        reviewRepository.deleteById(199L);
    }

    @Test
    public void getOrderList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).build();
        Page<Object[]> result = orderRepository.getOrderList(requestDto.getPageable(Sort.by("orderId")), 84L);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

}
