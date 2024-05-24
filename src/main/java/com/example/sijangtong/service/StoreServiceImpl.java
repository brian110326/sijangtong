package com.example.sijangtong.service;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;
import com.example.sijangtong.repository.StoreImgRepository;
import com.example.sijangtong.repository.StoreRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreImgRepository storeImgRepository;

  private final ReviewRepository reviewRepository;

  private final ProductRepository productRepository;

  private final StoreRepository storeRepository;

  private final OrderRepository orderRepository;

  private final OrderItemRepository orderItemRepository;

  private final ProductImgRepository productImgRepository;

  @Override
  public PageResultDto<StoreDto, Object[]> getStoreList(
      PageRequestDto pageRequestDto) {
    log.info("pageRequestDto  " + pageRequestDto);

    Page<Object[]> result = storeImgRepository.getTotalList(
        pageRequestDto.getType(),
        pageRequestDto.getKeyword(),
        pageRequestDto.getPageable(Sort.by("storeId").descending()));

    Function<Object[], StoreDto> fn = (en -> entityToDto(
        (Store) en[0],
        (List<StoreImg>) Arrays.asList((StoreImg) en[1])));

    return new PageResultDto<>(result, fn);
  }

  @Override
  public StoreDto getRow(Long storeId) {
    List<Object[]> result = storeImgRepository.getStoreRow(storeId);

    Store store = (Store) result.get(0)[0];

    // List<StoreImg> list = new ArrayList<>();
    // result.forEach(arr -> {
    // StoreImg storeImg = (StoreImg) arr[1];
    // list.add(storeImg);
    // });

    List<StoreImg> list = result
        .stream()
        .map(arr -> (StoreImg) arr[1])
        .collect(Collectors.toList());

    return entityToDto(store, list);
  }

  @Override
  @Transactional
  public Long removeStore(Long storeId) {

    Store store = storeRepository.findById(storeId).get();
    Optional<Product> pResult = productRepository.findByStore(store);

    if (pResult.isPresent()) {
      Product product = pResult.get();

      List<Review> reviews = reviewRepository.findByProduct(product);

      reviews.forEach(review -> {
        reviewRepository.delete(review);
      });

      // orderItem product 1:1로 바뀐거 확인하면 다시 바꾸기
      Optional<OrderItem> oResult = orderItemRepository.findByProduct(product);

      if (oResult.isPresent()) {
        OrderItem orderItem = oResult.get();

        orderItemRepository.delete(orderItem);
      }

      productImgRepository.deleteByProduct(product);

      productRepository.deleteByStore(store);
    }

    storeImgRepository.deleteByStore(store);

    orderRepository.deleteByStore(store);

    storeRepository.delete(store);

    return store.getStoreId();
  }

  // @Override
  // public PageResultDto<StoreDto, Object[]>
  // getStoreListByCategory(PageRequestDto pageRequestDto,
  // StoreCategory storeCategory) {
  // Page<Object[]> result = storeImgRepository
  // .getTotalListByCategory(pageRequestDto.getPageable(Sort.by("storeId").descending()),
  // storeCategory);

  // Function<Object[], StoreDto> fn = (en -> entityToDto((Store) en[0],
  // (List<StoreImg>) Arrays.asList((StoreImg) en[1])));

  // return new PageResultDto<>(result, fn);
  // }

  @Transactional
  @Override
  public Long storeInsert(StoreDto storeDto) {
    Map<String, Object> entityMap = dtoToentity(storeDto);

    // 스토어 삽입
    Store store = (Store) entityMap.get("store");
    storeRepository.save(store);

    // 스토어 이미지 삽입
    List<StoreImg> storeImgs = (List<StoreImg>) entityMap.get("imgList");
    storeImgs.forEach(image -> storeImgRepository.save(image));

    return store.getStoreId();
  }

  @Transactional
  @Override
  public Long storeUpdate(StoreDto storeDto) {
    Map<String, Object> entityMap = dtoToentity(storeDto);

    // 기존 스토어 이미지 제거
    Store store = (Store) entityMap.get("store");
    storeImgRepository.deleteByStore(store);

    storeRepository.save(store);

    // 스토어 이미지 삽입
    List<StoreImg> storeImgs = (List<StoreImg>) entityMap.get("imgList");
    storeImgs.forEach(image -> storeImgRepository.save(image));

    return store.getStoreId();
  }
}
