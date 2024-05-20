package com.example.sijangtong.service;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Rider;

public interface RiderService {
  // 라이더 추가
  Long createRider(RiderDto riderDto);
  // 라이더 정보 업데이트
  Long riderUpdate(RiderDto riderDto);
  // 라이더 삭제
  void deleteRideer(Long riderid);

  // 라이더 조회
  RiderDto riderRead(Long riderid);

  // 라이더측 오더 취소
  OrderDto riderOrderCancel(OrderDto orderDto);

  public default RiderDto entityToDto(Rider rider) {
    return RiderDto
      .builder()
      .riderId(rider.getRiderId())
      .riderName(rider.getRiderName())
      .riderTel(rider.getRiderTel())
      .riderStatus(rider.getRiderStatus())
      .build();
  }

  public default Rider dtoToEntity(RiderDto riderDto) {
    Rider rider = Rider
      .builder()
      .riderId(riderDto.getRiderId())
      .riderName(riderDto.getRiderName())
      .riderTel(riderDto.getRiderTel())
      .riderStatus(riderDto.getRiderStatus())
      .build();

    return rider;
  }
}
