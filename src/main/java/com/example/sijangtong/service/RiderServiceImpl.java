package com.example.sijangtong.service;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.RiderRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RiderServiceImpl implements RiderService {

  private final RiderRepository riderRepository;

  private final OrderRepository orderRepository;

  @Override
  public Long createRider(RiderDto riderDto) {
    Rider rider = dtoToEntity(riderDto);

    return riderRepository.save(rider).getRiderId();
  }

  @Override
  public Long riderUpdate(RiderDto riderDto) {
    Rider rider = dtoToEntity(riderDto);

    return riderRepository.save(rider).getRiderId();
  }

  @Override
  public void deleteRideer(Long riderid) {
    riderRepository.deleteById(riderid);
  }

  @Override
  public RiderDto riderRead(Long riderid) {
    return entityToDto(riderRepository.findById(riderid).get());
  }

  @Override
  @Transactional
  public void riderOrderCancel(OrderDto orderDto) {
    Order order = orderRepository.findById(orderDto.getOrderId()).get();

    order.setRider(null);
    order.setRiderOrdercancel(orderDto.getRiderOrdercancel());

    orderRepository.save(order);
  }

  // 라이더 리스트 구현

  @Override
  public PageResultDto<RiderDto, Rider> getRiderList(
    PageRequestDto pageRequestDto
  ) {
    // 이미지가 없는 유저 리스트를 가져오는 쿼리 실행
    Page<Rider> result = riderRepository.getRiderList(
      pageRequestDto.getPageable(Sort.by("riderId"))
    );

    // 페이지 결과 객체에 대한 변환 함수 정의
    Function<Rider, RiderDto> fn = rider -> entityToDto(rider);

    return new PageResultDto<>(result, fn);
  }
}
