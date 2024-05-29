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

    private final ReviewService reviewService;

    @GetMapping("/{storeId}/storeImages")
    public ResponseEntity<List<StoreImgDto>> getStore(@PathVariable("storeId") Long storeId) {

        log.info("storeId ==> {}", storeId);

        StoreDto storeDto = storeService.getRow(storeId);
        List<StoreImgDto> imgList = storeDto.getStoreImgDtos();

        return new ResponseEntity<List<StoreImgDto>>(imgList, HttpStatus.OK);
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
        PageResultDto<ProductDto, Object[]> list = productService.getProductList(requestDto, storeId);

        return new ResponseEntity<PageResultDto<ProductDto, Object[]>>(list, HttpStatus.OK);
    }

    // @GetMapping("/{storeId}/{productId}/product")
    // public ResponseEntity<ProductDto> getMethodName(@PathVariable("productId")
    // Long productId,
    // @PathVariable("storeId") Long storeId) {
    // ProductDto productDto = productService.getProductRow(productId);

    // return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    // }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<Page<ReviewDto>> getMethodName(@PathVariable("productId") Long productId,
            PageRequestDto pageRequestDto) {

        Product product = Product.builder().productId(productId).build();

        Page<ReviewDto> list = reviewService.getReviewsByProduct(product,
                pageRequestDto.getPageable(Sort.by("reviewId")));

        return new ResponseEntity<Page<ReviewDto>>(list, HttpStatus.OK);
    }

}
