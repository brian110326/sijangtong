package com.example.sijangtong.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.dto.MemberDto;
import com.example.sijangtong.dto.UpdatedPasswordDto;
import com.example.sijangtong.entity.Member;

public interface MemberService {

    List<Member> getWritableMembers(Long productId);

    // 회원가입
    String registerMember(MemberDto memberDto) throws IllegalStateException;

    // Nickname 수정
    void nickNameUpdate(MemberDto updateMemberDto);

    // Address 수정
    void addressUpdate(MemberDto updateMemberDto);

    // Password 수정
    void passwordUpdate(UpdatedPasswordDto pDto) throws IllegalStateException;

    // 회원탈퇴
    void memberWithdrawal(MemberDto memberDto) throws IllegalStateException;

    public default MemberDto entityToDto(Member member) {
        MemberDto memberDto = MemberDto.builder().memberEmail(member.getMemberEmail())
                .memberAddress(member.getMemberAddress()).memberNickname(member.getMemberNickname())
                .memberRole(member.getMemberRole())
                .memberPwd(member.getMemberPwd())
                .memberName(member.getMemberName())
                .memberTell(member.getMemberTell())
                .build();

        return memberDto;
    }

    public default Member dtoToEntity(MemberDto memberDto) {
        Member member = Member.builder().memberEmail(memberDto.getMemberEmail())
                .memberAddress(memberDto.getMemberAddress()).memberNickname(memberDto.getMemberNickname())
                .memberPwd((memberDto.getMemberPwd()))
                .memberName(memberDto.getMemberName())
                .memberTell(memberDto.getMemberTell())
                .memberRole(MemberRole.MEMBER)
                .build();

        return member;
    }

}
