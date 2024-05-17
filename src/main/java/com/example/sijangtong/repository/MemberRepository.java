package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.repository.memberTotal.MemberReviewRepository;

public interface MemberRepository extends JpaRepository<Member, String>, MemberReviewRepository {

}
