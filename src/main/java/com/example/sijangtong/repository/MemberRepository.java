package com.example.sijangtong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.memberTotal.MemberReviewRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberReviewRepository {

    Optional<Member> findByMemberEmail(String memberEmail);

    @Modifying
    @Query("update Member m set m.memberNickname = :memberNickname where m.memberEmail = :memberEmail")
    void updateNickname(String memberNickname, String memberEmail);

    @Modifying
    @Query("update Member m set m.memberAddress = :memberAddress where m.memberEmail = :memberEmail")
    void updateAddress(String memberAddress, String memberEmail);
    
    @Modifying
    @Query("delete from Member m where m.store = :store")
    void deleteByStore(Store store);
}
