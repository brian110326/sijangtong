package com.example.sijangtong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedPasswordDto {

    private String memberEmail;

    private String currentPwd;

    private String newPwd;
}
