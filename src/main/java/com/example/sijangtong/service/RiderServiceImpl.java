package com.example.sijangtong.service;

import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.RiderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
}
