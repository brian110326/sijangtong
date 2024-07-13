package com.example.sijangtong.dto;

import com.example.sijangtong.constant.StoreCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

  private Long storeId;

  @NotNull(message = "카테고리 값을 넣어주세요")
  private StoreCategory storeCategory;

  @NotBlank(message = "시장 전화번호는 필수입니다")
  private String storeTel;

  @NotBlank(message = "개점시간은 필수입니다")
  private String openTime;

  @NotBlank(message = "폐점시간은 필수입니다")
  private String closeTime;

  @NotBlank(message = "주소 넣어주세요")
  private String storeAddress;

  @NotBlank(message = "이름을 넣어주세요")
  private String storeName;

  @NotBlank(message = "디테일 넣어주세요")
  private String storeDetail;

  private double gradeAvg;

  @Builder.Default
  private List<StoreImgDto> storeImgDtos = new ArrayList<>();
}
