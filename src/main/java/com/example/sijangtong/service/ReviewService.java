package com.example.sijangtong.service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
  // review list 보여주기
  List<ReviewDto> getReviewList(
      PageRequestDto pageRequestDto,
      Long productId);

  // review 삭제
  Long removeReview(Long reviewId);

  // review의 text, grade 수정
  Long updateReview(ReviewDto reviewDto);

  // review 등록
  Long createReview(ReviewDto reviewDto);

  // 테스트용
  Page<ReviewDto> getReviewsByProduct(Product product, Pageable pageable);

  public default ReviewDto entityToDto(Review review) {
    ReviewDto reviewDto = ReviewDto
        .builder()
        .reviewId(review.getReviewId())
        .text(review.getText())
        .grade(review.getGrade())
        .productId(review.getProduct().getProductId())
        .memberEmail(review.getMember().getMemberEmail())
        .memberNickname(review.getMember().getMemberNickname())
        .createdDate(review.getCreatedDate())
        .lastModifiedDate(review.getLastModifiedDate())
        .build();

    return reviewDto;
  }

  public default Review dtoToEntity(ReviewDto reviewDto) {
    Member member = Member
        .builder()
        .memberEmail(reviewDto.getMemberEmail())
        .memberNickname(reviewDto.getMemberNickname())
        .build();

    Product product = Product
        .builder()
        .productId(reviewDto.getProductId())
        .build();

    Review review = Review
        .builder()
        .reviewId(reviewDto.getReviewId())
        .text(reviewDto.getText())
        .grade(reviewDto.getGrade())
        .member(member)
        .product(product)
        .build();

    review.setCreatedDate(reviewDto.getCreatedDate());
    review.setLastModifiedDate(reviewDto.getLastModifiedDate());

    return review;
  }
}
