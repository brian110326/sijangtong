package com.example.sijangtong.service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  private final ProductRepository productRepository;

  @Override
  public List<ReviewDto> getReviewList(
      PageRequestDto pageRequestDto,
      Long productId) {

    Page<Object[]> result = reviewRepository.getReviewList(
        pageRequestDto.getPageable(Sort.by("reviewId")),
        productId);
    // log.info("page result : {}", result);

    List<ReviewDto> reviewDtoList = result.getContent().stream()
        .map(entity -> entityToDto((Review) entity[1]))
        .collect(Collectors.toList());

    return reviewDtoList;
  }

  @Override
  public Long removeReview(Long reviewId) {
    reviewRepository.deleteById(reviewId);

    return reviewId;
  }

  @Override
  public Long updateReview(ReviewDto reviewDto) {
    reviewRepository.updateReview(
        reviewDto.getText(),
        reviewDto.getGrade(),
        reviewDto.getReviewId());

    return reviewDto.getReviewId();
  }

  @Override
  public Long createReview(ReviewDto reviewDto) {
    Review review = dtoToEntity(reviewDto);

    Review newReview = reviewRepository.save(review);

    return newReview.getReviewId();
  }

  @Override
  public PageResultDto<ReviewDto, Object[]> getReviewList2(PageRequestDto pageRequestDto, Long productId) {

    Page<Object[]> result = reviewRepository.getReviewList(pageRequestDto.getPageable(Sort.by("reviewId")), productId);

    Function<Object[], ReviewDto> fn = (en -> entityToDto((Review) en[1]));

    return new PageResultDto<>(result, fn);

  }

  @Override
  public ReviewDto getReview(Long reviewId) {
    return entityToDto(reviewRepository.findById(reviewId).get());
  }

}
