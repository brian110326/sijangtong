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
    storeImgRepository.deleteBystore(store);
    storeRepository.save(store);

    // 스토어 이미지 삽입
    List<StoreImg> storeImgs = (List<StoreImg>) entityMap.get("imgList");
    storeImgs.forEach(image -> storeImgRepository.save(image));

    return store.getStoreId();
  }

  @Override
  public PageResultDto<StoreDto, Object[]> getStoreList(
    PageRequestDto pageRequestDto
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'getStoreList'"
    );
  }

  @Override
  public StoreDto getRow(Long storeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRow'");
  }
}
