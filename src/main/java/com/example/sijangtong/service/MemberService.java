package com.example.sijangtong.service;

import com.example.sijangtong.dto.MemberDto;
import com.example.sijangtong.entity.Member;

public interface MemberService {

    public default Member dtoToEntity(MemberDto dto) {
        return Member.builder()

                .build();

    }

}
