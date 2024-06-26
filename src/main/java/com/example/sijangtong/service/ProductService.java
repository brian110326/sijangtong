package com.example.sijangtong.service;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductService {
  PageResultDto<ProductDto, Object[]> getProductList(
      PageRequestDto pageRequestDto,
      Long storeId);

  Long productInsert(ProductDto productDto);

  Long productUpdate(ProductDto productDto) throws IllegalStateException;

  ProductDto getProductRow(Long productId);

  Long removeProduct(Long productId);

  // transient Exception방지용
  Product getProductById(Long productId);

  public default ProductDto entityToDto(
      Product product,
      List<ProductImg> productImgs,
      Store store,
      Double avg) {

    String formatAvg = avg != null ? String.format("%.1f", avg) : "0.0"; // 리뷰평점

    ProductDto productDto = ProductDto
        .builder()
        .productId(product.getProductId())
        .pName(product.getPName())
        .price(product.getPrice())
        .amount(product.getAmount())
        .avg(Double.valueOf(formatAvg))
        .storeId(product.getStore().getStoreId())
        .avg(avg != null ? avg : 0.0)
        // .quantity(product.getQuantity())
        .build();

    List<ProductImgDto> productImgDtos = productImgs
        .stream()
        .map(pImg -> {
          return ProductImgDto
              .builder()
              .imgId(pImg.getImgId())
              .uuid(pImg.getUuid())
              .imgName(pImg.getImgName())
              .path(pImg.getPath())
              .build();
        })
        .collect(Collectors.toList());

    productDto.setProductImgDtos(productImgDtos);

    return productDto;
  }

  public default Map<String, Object> dtoToEntity(ProductDto dto) {
    Map<String, Object> entityMap = new HashMap<>();

    Product product = new Product();
    product.setProductId(dto.getProductId());
    product.setPName(dto.getPName());
    product.setAmount(dto.getAmount());
    product.setPrice(dto.getPrice());
    // product.setQuantity(dto.getQuantity());

    product.setStore(Store.builder().storeId(dto.getStoreId()).build());

    entityMap.put("product", product);
    entityMap.put("Service getStoreId", dto.getStoreId());

    List<ProductImgDto> productImgDtos = dto.getProductImgDtos();

    if (productImgDtos != null && productImgDtos.size() > 0) {
      List<ProductImg> productImgs = productImgDtos
          .stream()
          .map(pDto -> {
            ProductImg productImg = ProductImg
                .builder()
                .product(product)
                .imgName(pDto.getImgName())
                .uuid(pDto.getUuid())
                .path(pDto.getPath())
                .build();
            return productImg;
          })
          .collect(Collectors.toList());

      entityMap.put("imgList", productImgs);
    }

    // 변환이 끝난 entity list를 Map 담기 : put()
    return entityMap;
  }
}
