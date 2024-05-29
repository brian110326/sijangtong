package com.example.sijangtong.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sijangtong.constant.MemberRole;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.AuthMemberDto;
import com.example.sijangtong.dto.MemberDto;
import com.example.sijangtong.dto.UpdatedPasswordDto;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.repository.MemberRepository;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.OrderRepository;
import com.example.sijangtong.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Log in 요청 : {}", username);

        Optional<Member> result = memberRepository.findByMemberEmail(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("Email Not Found");
        }

        Member member = result.get();

        return new AuthMemberDto(entityToDto(member));
    }

    // 중복 이메일 검사
    public void validateDuplicateEmail(String memberEmail) throws IllegalStateException {

        Optional<Member> result = memberRepository.findByMemberEmail(memberEmail);

        if (result.isPresent()) {
            throw new IllegalStateException("Email With This Member Already Exists!");
        }
    }

    // 회원가입
    @Override
    public String registerMember(MemberDto memberDto) {

        Member member = Member.builder().memberEmail(memberDto.getMemberEmail())
                .memberAddress(memberDto.getMemberAddress()).memberNickname(memberDto.getMemberNickname())

                .memberPwd(passwordEncoder.encode(memberDto.getMemberPwd())).memberRole(MemberRole.MEMBER)

                .build();

        validateDuplicateEmail(member.getMemberEmail());

        Member newMember = memberRepository.save(member);

        return newMember.getMemberEmail();
    }

    // 닉네임 수정
    @Override
    @Transactional
    public void nickNameUpdate(MemberDto updateMemberDto) {

        memberRepository.updateNickname(updateMemberDto.getMemberNickname(),
                updateMemberDto.getMemberEmail());
    }

    // 주소 수정
    @Override
    @Transactional
    public void addressUpdate(MemberDto updateMemberDto) {

        memberRepository.updateAddress(updateMemberDto.getMemberAddress(),
                updateMemberDto.getMemberEmail());
    }

    // 비밀번호 수정
    @Override
    public void passwordUpdate(UpdatedPasswordDto pDto) throws IllegalStateException {

        Member member = memberRepository.findByMemberEmail(pDto.getMemberEmail()).get();

        // 비밀번호가 일치하지 않을 때 / 일치할 때
        if (!passwordEncoder.matches(pDto.getCurrentPwd(), member.getMemberPwd())) {
            throw new IllegalStateException("Password does not match");
        } else {
            member.setMemberPwd(passwordEncoder.encode(pDto.getNewPwd()));
            memberRepository.save(member);
        }

    }

    // 회원 탈퇴
    @Override
    @Transactional
    public void memberWithdrawal(MemberDto memberDto) throws IllegalStateException {

        Member member = memberRepository.findByMemberEmail(memberDto.getMemberEmail()).get();
        Order order = orderRepository.findByMember(member).get();

        if (!passwordEncoder.matches(memberDto.getMemberPwd(),
                member.getMemberPwd())) {
            throw new IllegalStateException("Password does not match");
        } else {

            reviewRepository.deleteByMember(member);

            orderItemRepository.deleteByOrder(order);
            orderRepository.delete(order);

            memberRepository.delete(member);
        }
    }

}
