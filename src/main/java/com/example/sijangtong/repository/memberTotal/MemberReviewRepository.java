package com.example.sijangtong.repository.memberTotal;

import java.util.List;

import com.example.sijangtong.entity.Member;

public interface MemberReviewRepository {

    List<Member> writableMembers(Long productId);
}
