package com.example.sijangtong.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ProductImgDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;

public interface ProductService {

    PageResultDto<ProductDto, Object[]> getProductList(PageRequestDto pageRequestDto, Long storeId);

    void productRemove(Long productId);

    Long productInsert(ProductDto productDto);

    ProductDto getProductRow(Long productId);

    public default ProductDto entityToDto(Product product, List<ProductImg> productImgs) {
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .pName(product.getPName())
                .price(product.getPrice())
                .amount(product.getAmount())
                .storeId(product.getStore().getStoreId())
                .build();

        return productDto;
    }

    public default Map<String, Object> dtoToEntity(ProductDto dto) {

        Map<String, Object> entityMap = new HashMap<>();

        Product product = new Product();
        product.setPName(dto.getPName());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
        product.setStore(Store.builder().storeId(dto.getStoreId()).build());

        entityMap.put("product", product);

        List<ProductImgDto> productImgDtos = dto.getProductImgDtos();

        if (productImgDtos != null && productImgDtos.size() > 0) {
            List<ProductImg> productImgs = productImgDtos.stream().map(pDto -> {
                ProductImg productImg = ProductImg.builder()
                        .imgName(pDto.getImgName())
                        .uuid(pDto.getUuid())
                        .path(pDto.getPath())
                        .product(product)
                        .build();
                return productImg;
            }).collect(Collectors.toList());

            entityMap.put("imgList", productImgs);
        }

        // 변환이 끝난 entity list를 Map 담기 : put()
        return entityMap;
    }

}
