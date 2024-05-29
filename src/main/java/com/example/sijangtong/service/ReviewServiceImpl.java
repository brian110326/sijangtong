package com.example.sijangtong.service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
  public Page<ReviewDto> getReviewsByProduct(Product product, Pageable pageable) {

    Page<Review> result = reviewRepository.findByProduct(product, pageable);

    // Review 객체를 ReviewDto로 변환하여 담을 리스트 생성
    List<ReviewDto> reviewDtoList = new ArrayList<>();

    // 각 Review 객체를 ReviewDto로 변환 후 리스트에 추가
    result.forEach(review -> {
      ReviewDto reviewDto = entityToDto(review);
      reviewDtoList.add(reviewDto);
    });

    // ReviewDto 리스트와 페이징 정보로 새로운 Page 객체 생성하여 반환
    return new PageImpl<>(reviewDtoList, pageable, result.getTotalElements());
  }

}
