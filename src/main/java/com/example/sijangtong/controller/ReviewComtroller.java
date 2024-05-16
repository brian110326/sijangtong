package com.example.sijangtong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Log4j2
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewComtroller {

    private final ReviewService reviewService;

    // 스토어 하나의 전체 리뷰 가져오기
    @GetMapping("/{storeId}")
    public ResponseEntity<List<ReviewDto>> getReview(@PathVariable("storeId") Long storeId) {
        log.info("전체 리스트 {}", storeId);

        PageResultDto<ReviewDto, Object[]> result = reviewService.getReviewList(null, storeId);

    return new ResponseEntity<List<ReviewDto>>(HttpStatusCode.OK)
        
    }

    // 리뷰 등록
    @PostMapping("/{storeId}")
    public ResponseEntity<Long> postMethodName(@RequestBody ReviewDto reviewDto) {
        log.info("리뷰 등록 {}", reviewDto);

    }

    // 리뷰 삭제
    @DeleteMapping("/{storeId}/{reviewId}")
    public ResponseEntity<Long> deleteReview(@PathVariable("reviewId") Long reviewId, String memberEmail) {
        log.info("리뷰 삭제 {}", reviewId);

        reviewService.removeReview(reviewId);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    // 리뷰 수정

}
