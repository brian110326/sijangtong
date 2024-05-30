package com.example.sijangtong.service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

  private final OrderItemRepository orderItemRepository;

  private final ReviewRepository reviewRepository;

  @Override
  public PageResultDto<ProductDto, Object[]> getProductList(
    PageRequestDto pageRequestDto,
    Long storeId
  ) {
    Page<Object[]> result = productImgRepository.getProductList(
      pageRequestDto.getType(),
      pageRequestDto.getKeyword(),
      pageRequestDto.getPageable(Sort.by("productId")),
      storeId
    );

    Function<Object[], ProductDto> fn =
      (
        en ->
          entityToDto(
            (Product) en[0],
            (List<ProductImg>) Arrays.asList((ProductImg) en[1]),
            (Store) en[2],
            (Double) en[3]
          )
      );

    return new PageResultDto<>(result, fn);
  }

  @Override
  public ProductDto getProductRow(Long productId) {
    List<Object[]> result = productImgRepository.getProductRow(productId);

    Product product = (Product) result.get(0)[0];

    List<ProductImg> list = result
      .stream()
      .map(en -> (ProductImg) en[1])
      .collect(Collectors.toList());

    Store store = (Store) result.get(0)[2];

    Double avg = (Double) result.get(0)[3];

    return entityToDto(product, list, store, avg);
  }

  @Override
  @Transactional
  public Long removeProduct(Long productId) {
    Product product = productRepository.findById(productId).get();
    // Optional<OrderItem> oResult = orderItemRepository.findByProduct(product);

    productImgRepository.deleteByProduct(product);

    // product 삭제 시 orderItem안 product항목만 null로 설정
    // orderItems.forEach(orderItem -> orderItem.setProduct(null));
    // orderItemRepository.saveAll(orderItems);

    // if (oResult.isPresent()) {
    // OrderItem orderItem = oResult.get();

    // orderItemRepository.delete(orderItem);
    // }

    orderItemRepository.deleteByProduct(product);

    reviewRepository.deleteByProduct(product);

    productRepository.delete(product);

    // ex) return 값이 Long 이유 : 200번 삭제성공 alert창(예시)
    return product.getProductId();
  }

  @Transactional
  @Override
  public Long productInsert(ProductDto productDto) {
    Map<String, Object> entityMap = dtoToEntity(productDto);

    System.out.println("product" + entityMap.get("product"));
    System.out.println("imgList" + entityMap.get("imgList"));

    // 상품 삽입
    Product product = (Product) entityMap.get("product");
    product = productRepository.save(product);

    // 상품 이미지 삽입
    List<ProductImg> productImgs = (List<ProductImg>) entityMap.get("imgList");
    productImgs.forEach(image -> productImgRepository.save(image));

    return product.getProductId();
  }

  @Transactional
  @Override
  public Long productUpdate(ProductDto productDto) throws IllegalStateException {
    // dto ==> entity
    Map<String, Object> entityMap = dtoToEntity(productDto);

    Product product = (Product) entityMap.get("product");
    productImgRepository.deleteByProduct(product);

    // 임시 추가
    productRepository.save(product);

    // movie image 삽입
    List<ProductImg> productImgs = (List<ProductImg>) entityMap.get("imgList");
    productImgs.forEach(image -> productImgRepository.save(image));

    // productRepository.save(product);

    return product.getProductId();
  }

  @Override
  public Product getProductById(Long productId) {
    Optional<Product> result = productRepository.findById(productId);

    return result.get();
  }
}
// 주문 자체는 살려놔야 한다 주문 번호등 주문 아이템등등
// 해당 오더를 할당 받는다고 해도 사실상 할당 받는다는 것은
// 오더 테이블에 라이더 아이디 값이 추가되는 것 뿐임
// 그렇다면 그 오더테이블에 라이더 아이디 컬럼 값을 다시 null 로 만들면 되는 것이다
// 해야 할 것 정리 : 오더 엔티티에 컬럼 하나 추가 1) ENUM 클래스로 만들고
