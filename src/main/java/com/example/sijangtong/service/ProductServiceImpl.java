package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductImgRepository productImgRepository;

    @Override
    public PageResultDto<ProductDto, Object[]> getProductList(PageRequestDto pageRequestDto, Long storeId) {
        Page<Object[]> result = productImgRepository.getProductList(pageRequestDto.getPageable(Sort.by("productId")),
                storeId);

        Function<Object[], ProductDto> fn = (en -> entityToDto((Product) en[0],
                (List<ProductImg>) Arrays.asList((ProductImg) en[1])));

        return new PageResultDto<>(result, fn);

    }

    @Override
    public ProductDto getProductRow(Long productId) {
        List<Object[]> result = productImgRepository.getProductRow(productId);

        Product product = (Product) result.get(0)[0];

        List<ProductImg> list = result.stream().map(en -> (ProductImg) en[1]).collect(Collectors.toList());

        return entityToDto(product, list);
    }

}
