package com.example.sijangtong.dto;

import com.example.sijangtong.constant.MemberRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  @Email(message = "이메일 형식이 아닙니다.")
  @NotBlank(message = "이메일은 필수 요소입니다.")
  private String memberEmail;

  @NotBlank(message = "닉네임은 필수 요소입니다.")
  private String memberNickname;

  @NotBlank(message = "닉네임은 필수 요소입니다.")
  private String memberAddress;

  @NotBlank(message = "비밀번호는 필수 요소입니다.")
  private String memberPwd;

  private Long storeId;

  private MemberRole memberRole;
}
