package com.example.sijangtong.entity;

import com.example.sijangtong.constant.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {

  @Id
  private String memberEmail;

  @Column(nullable = false)
  private String memberNickname;

  @Column(nullable = false)
  private String memberAddress;

  @Column(nullable = false)
  private String memberPwd;

  @Enumerated(EnumType.STRING)
  private MemberRole memberRole;

  private String memberName;

  private String memberTell;

  // 오너 회원을 위한 storeId 컬럼
  @Nullable
  @OneToOne
  private Store store;
}
