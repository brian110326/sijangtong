package com.example.sijangtong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> getWritableMembers(Long productId) {

        List<Member> members = memberRepository.writableMembers(productId);

        return members;

        // service 설명 및 활용법
        // review를 등록할 때 특정 제품을 구매한 member들만 review를 등록할 수 있게함
        // ex) 8번 상품을 구매한 고객들 => List<Member>

        // 활용법
        // 1) controller에서 이 service를 호출한다. ==> List<Member> 반환
        // 2) security를 이용하여 authentication을 통해 member의 정보를 가져온다.
        // 3) 가져온 member가 List<Member> 안에 있으면 댓글 등록 가능하게 하고 댓글 등록 부분을 화면에 띄워준다.
        // 4) List<Member>에 없다면 댓글 등록 부분을 화면에 보여주지 않는다.

    }

}
