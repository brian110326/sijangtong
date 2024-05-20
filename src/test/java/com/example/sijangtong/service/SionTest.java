package com.example.sijangtong.service;

import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Rider;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SionTest {

  @Autowired
  private RiderService riderService;

  // 라이더 생성 테스트 5월 20일 정상 작동 완료
  @Test
  public void riderinsertTest() {
    RiderDto riderDto = RiderDto
      .builder()
      .riderName("테스트 배달기사 1")
      .riderStatus(RiderStatus.DELIVERING)
      .riderTel("테스트 전화번호")
      .build();
    riderService.createRider(riderDto);
  }

  // 라이더 생성 테스트 5월 20일 정상 작동 완료
  @Test
  public void riderUpdateTest() {
    RiderDto riderDto = RiderDto
      .builder()
      .riderId(1L)
      .riderName("테스트 배달기사  업데이트 1")
      .riderStatus(RiderStatus.WAITING)
      .riderTel("테스트   업데이트 전화번호")
      .build();
    riderService.riderUpdate(riderDto);
  }

  // 라이더 생성 테스트 5월 20일 정상 작동 완료
  @Test
  public void riderDeleteTest() {
    riderService.deleteRideer(2L);
  }

  // 라이더 생성 테스트 5월 20일 정상 작동 완료
  @Test
  public void riderReadTest() {
    RiderDto result = riderService.riderRead(1L);
    System.out.println(result);
  }
}
