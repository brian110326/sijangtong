package com.example.sijangtong.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Store;

public interface ProductService {

    PageResultDto<ProductDto, Object[]> getProductList(PageRequestDto pageRequestDto, Long storeId);

    ProductDto getProductRow(Long productId);

    Long removeProduct(Long productId);

    public default ProductDto entityToDto(Product product, List<ProductImg> productImgs) {
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .pName(product.getPName())
                .price(product.getPrice())
                .amount(product.getAmount())
                .storeId(product.getStore().getStoreId())
                .build();

        List<ProductImgDto> productImgDtos = productImgs.stream().map(pImg -> {
            return ProductImgDto.builder().imgId(pImg.getImgId()).uuid(pImg.getUuid()).imgName(pImg.getImgName())
                    .path(pImg.getPath())
                    .build();
        }).collect(Collectors.toList());

        productDto.setProductImgDtos(productImgDtos);

        return productDto;
    }

}
