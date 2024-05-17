package com.example.sijangtong.service;

import java.util.List;

import com.example.sijangtong.dto.MemberDto;
import com.example.sijangtong.entity.Member;

public interface MemberService {

    List<Member> getWritableMembers(Long productId);

}
