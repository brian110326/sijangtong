package com.example.sijangtong.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sijangtong.dto.AuthMemberDto;
import com.example.sijangtong.dto.MemberDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.UpdatedPasswordDto;
import com.example.sijangtong.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/login")
    public void getLogin(@ModelAttribute("requestDto") PageRequestDto pageRequestDto,
            @RequestParam(required = false, value = "memberEmail") String memberEmail) {
        log.info("로그인 폼 요청");
    }

    @GetMapping("/register")
    public void getRegister(MemberDto memberDto, @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
            @RequestParam(required = false, value = "memberEmail") String memberEmail) {
        log.info("회원가입 폼 요청");
    }

    // 회원가입 요청 post
    @PostMapping("/register")
    public String postRegister(@Valid MemberDto dto, BindingResult result, RedirectAttributes rttr,
            @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("회원가입 요청 {}", dto);

        if (result.hasErrors())
            return "/member/register";

        String email = "";
        try {
            email = service.registerMember(dto);
        } catch (Exception e) {
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/register";
        }

        rttr.addFlashAttribute("email", email);
        return "redirect:/member/login";
    }

    // 프로필 화면 출력
    @PreAuthorize("isAuthenticated()")
    @GetMapping("profile")
    public void getProfile(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("프로필 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/all")
    public String postEditProfile(MemberDto updateMemberDto, UpdatedPasswordDto pDto,
            @ModelAttribute("requestDto") PageRequestDto pageRequestDto, RedirectAttributes rttr)
            throws IllegalStateException {
        log.info("수정 폼 요청");

        service.passwordUpdate(pDto);

        service.addressUpdate(updateMemberDto);
        service.nickNameUpdate(updateMemberDto);

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        AuthMemberDto authMemberDto = (AuthMemberDto) authentication.getPrincipal();
        authMemberDto.getMemberDto().setMemberNickname(updateMemberDto.getMemberNickname());
        authMemberDto.getMemberDto().setMemberAddress(updateMemberDto.getMemberAddress());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/member/profile";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("leave")
    public void getLeave(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("계정 삭제 폼 요청");
    }

    @PostMapping("/leave")
    public String postLeave(MemberDto leaveMemberDto, RedirectAttributes rttr, HttpSession session) {
        log.info("회원탈퇴 {}", leaveMemberDto);

        try {
            service.memberWithdrawal(leaveMemberDto);
        } catch (Exception e) {
            rttr.addFlashAttribute("error", "이메일이나 비밀번호를 확인해 주세요");
        }
        session.invalidate();

        return "redirect:/";
    }

}
