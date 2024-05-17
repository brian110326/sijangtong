package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductImgRepository productImgRepository;

    private final ProductRepository productRepository;

    // @Override
    // public PageResultDto<ProductDto, Object[]> getProductList(PageRequestDto
    // pageRequestDto, Long storeId) {
    // Page<Object[]> result =
    // productImgRepository.getProductList(pageRequestDto.getPageable(Sort.by("productId")),
    // storeId);

    // Function<Object[], ProductDto> fn = (en -> entityToDto((Product) en[0],
    // (List<ProductImg>) Arrays.asList((ProductImg) en[1])));

    // return new PageResultDto<>(result, fn);

    // }

    // @Override
    // public ProductDto getProductRow(Long productId) {
    // List<Object[]> result = productImgRepository.getProductRow(productId);

    // Product product = (Product) result.get(0)[0];

    // List<ProductImg> list = result.stream().map(en -> (ProductImg)
    // en[1]).collect(Collectors.toList());

    // return entityToDto(product, list);
    // }

    @Transactional
    @Override
    public Long productInsert(ProductDto productDto) {

        Map<String, Object> entityMap = dtoToEntity(productDto);

        System.out.println("product" + entityMap.get("product"));
        System.out.println("imgList" + entityMap.get("imgList"));

        // 상품 삽입
        Product product = (Product) entityMap.get("product");
        productRepository.save(product);

        // 상품 이미지 삽입
        List<ProductImg> productImgs = (List<ProductImg>) entityMap.get("imgList");
        productImgs.forEach(image -> productImgRepository.save(image));

        return product.getProductId();

    }

    @Transactional
    @Override
    public Long productUpdate(ProductDto productDto) {

        // dto ==> entity
        Map<String, Object> entityMap = dtoToEntity(productDto);

        Product product = (Product) entityMap.get("product");
        productImgRepository.deleteByProduct(product);

        // movie image 삽입
        List<ProductImg> productImgs = (List<ProductImg>) entityMap.get("imgList");
        productImgs.forEach(image -> productImgRepository.save(image));

        productRepository.save(product);

        return product.getProductId();

    }

}
