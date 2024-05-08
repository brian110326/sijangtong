package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sijangtong.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
