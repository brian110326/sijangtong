package com.example.sijangtong.service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;

public interface ReviewService {
    PageResultDto<ReviewDto, Object[]> getReviewList(PageRequestDto pageRequestDto, Long storeId);

    public default ReviewDto entityToDto(Store store, Product product, Review review, Order order) {
        ReviewDto reviewDto = ReviewDto.builder().reviewId(review.getReviewId())
                .text(review.getText())
                .grade(review.getGrade())
                .storeId(review.getStore().getStoreId())
                .memberEmail(review.getMember().getMemberEmail())
                .memberNickname(review.getMember().getMemberNickname())
                .createdDate(review.getCreatedDate())
                .lastModifiedDate(review.getLastModifiedDate())
                .build();

        return reviewDto;

    }
}
