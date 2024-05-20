package com.example.sijangtong.service;

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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductImgRepository productImgRepository;

  private final ProductRepository productRepository;

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
// 주문 자체는 살려놔야 한다 주문 번호등 주문 아이템등등
// 해당 오더를 할당 받는다고 해도 사실상 할당 받는다는 것은
// 오더 테이블에 라이더 아이디 값이 추가되는 것 뿐임
// 그렇다면 그 오더테이블에 라이더 아이디 컬럼 값을 다시 null 로 만들면 되는 것이다
// 해야 할 것 정리 : 오더 엔티티에 컬럼 하나 추가 1) ENUM 클래스로 만들고
