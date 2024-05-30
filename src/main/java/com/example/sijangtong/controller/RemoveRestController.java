package com.example.sijangtong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.service.ProductService;
import com.example.sijangtong.service.ReviewService;
import com.example.sijangtong.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/store")
public class RemoveRestController {

    private final StoreService storeService;

    private final ProductService productService;

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDto> getStore(@PathVariable("storeId") Long storeId) {

        log.info("storeId ==> {}", storeId);

        StoreDto storeDto = storeService.getRow(storeId);

        return new ResponseEntity<StoreDto>(storeDto, HttpStatus.OK);
    }

    @GetMapping("/{productId}/product")
    public ResponseEntity<ProductDto> getMethodName(@PathVariable("productId") Long productId) {

        ProductDto productDto = productService.getProductRow(productId);

        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    @GetMapping("/{storeId}/products")
    public ResponseEntity<PageResultDto<ProductDto, Object[]>> getProducts(@PathVariable("storeId") Long storeId,
            PageRequestDto requestDto) {

        PageResultDto<ProductDto, Object[]> list = productService.getProductList(requestDto, storeId);

        return new ResponseEntity<PageResultDto<ProductDto, Object[]>>(list, HttpStatus.OK);
    }

    @GetMapping("/{storeId}/products/{page}")
    public ResponseEntity<PageResultDto<ProductDto, Object[]>> getProductsPage(@PathVariable("storeId") Long storeId,
            @PathVariable("page") int page, PageRequestDto requestDto) {

        log.info("storeId {},{}", storeId, page);

        PageResultDto<ProductDto, Object[]> list = productService.getProductList(requestDto, storeId);

        return new ResponseEntity<PageResultDto<ProductDto, Object[]>>(list, HttpStatus.OK);
    }

    // @GetMapping("/{productId}/reviews")
    // public ResponseEntity<PageResultDto<ReviewDto, Object[]>>
    // getReviews(@PathVariable("productId") Long productId,
    // PageRequestDto pageRequestDto) {

    // PageResultDto<ReviewDto, Object[]> result =
    // reviewService.getReviewList2(pageRequestDto, productId);

    // return new ResponseEntity<PageResultDto<ReviewDto, Object[]>>(result,
    // HttpStatus.OK);
    // }

    // @GetMapping("/{productId}/reviews/{rPage}")
    // public ResponseEntity<PageResultDto<ReviewDto, Object[]>>
    // getReviewsPage(@PathVariable("productId") Long productId,
    // @PathVariable("rPage") int rPage,
    // PageRequestDto pageRequestDto) {

    // PageResultDto<ReviewDto, Object[]> result =
    // reviewService.getReviewList2(pageRequestDto, productId);

    // return new ResponseEntity<PageResultDto<ReviewDto, Object[]>>(result,
    // HttpStatus.OK);
    // }

}
