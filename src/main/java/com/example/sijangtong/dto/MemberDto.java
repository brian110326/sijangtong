package com.example.sijangtong.dto;

import com.example.sijangtong.constant.MemberRole;

import jakarta.persistence.Column;
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

  @NotBlank(message = "이름은 필수 요소입니다.")
  private String memberName;

  @NotBlank(message = "전화번호는 필수 요소입니다.")
  private String memberTell;

  private Long storeId;

  private MemberRole memberRole;
}
