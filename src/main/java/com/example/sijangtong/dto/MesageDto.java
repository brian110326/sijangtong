package com.example.sijangtong.dto;

import com.example.sijangtong.constant.RiderStatus;
import jakarta.persistence.Column;
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
public class MesageDto {

  private String subject;

  private String text;

  private String meberEmail;

  private String memberName;
}
