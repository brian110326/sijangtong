package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<ReviewDto, Object[]> getReviewList(PageRequestDto pageRequestDto, Long storeId) {
        Page<Object[]> result = reviewRepository.getReviewList(pageRequestDto.getPageable(Sort.by("reviewId")),
                storeId);

        Function<Object[], ReviewDto> fn = (en -> entityToDto((Product) en[0],
                (Review) en[1]));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long removeReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);

        return reviewId;
    }

    @Override
    public Long updateReview(ReviewDto reviewDto) {
        Review updatedReview = reviewRepository.save(dtoToEntity(reviewDto));

        return updatedReview.getReviewId();
    }

    @Override
    public Long createReview(ReviewDto reviewDto) {
        Review review = dtoToEntity(reviewDto);

        Review newReview = reviewRepository.save(review);

        return newReview.getReviewId();
    }

}
