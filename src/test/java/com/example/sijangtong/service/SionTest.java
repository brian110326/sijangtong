package com.example.sijangtong.service;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.constant.RiderOrdercancel;
import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.dto.OrderDto;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class SionTest {

  @Autowired
  private RiderService riderService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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

  @Commit
  @Test
  public void riderOrdercancelTest() {
    OrderDto orderDto = OrderDto
      .builder()
      .orderId(3l)
      .riderOrdercancel(RiderOrdercancel.ACCIDENT)
      .build();

    riderService.riderOrderCancel(orderDto);
  }

  // 머지 전 계정 생성 메소드
  @Test
  public void insertMemberTest() {
    Member member = Member
      .builder()
      .memberEmail("z1130ion@naver.com")
      .memberNickname("김시온(admin)")
      .memberAddress("종로")
      .memberPwd(passwordEncoder.encode("1111"))
      .memberRole(MemberRole.ADMIN)
      .build();

    memberRepository.save(member);
  }
}
