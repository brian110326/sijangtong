package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.repository.memberTotal.MemberReviewRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberReviewRepository {

    Optional<Member> findByMemberEmail(String memberEmail);
}
