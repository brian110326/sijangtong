package com.example.sijangtong.dto;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Store;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

  private Long reviewId;

  private String text;

  private int grade;

  // Store와의 관계
  private Long productId;

  // 멤버와의 관계
  private String memberEmail;
  private String memberNickname;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
