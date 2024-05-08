package com.example.sijangtong.dto;

import com.example.sijangtong.constant.MemberRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String memberEmail;

    private String memberNickname;

    private String memberAddress;

    private String memberPwd;

    private MemberRole memberRole;
}
