package com.example.sijangtong.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sijangtong.dto.PageRequestDto;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void getLogin(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {

    }

    @GetMapping("/register")
    public void getRegister(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {

    }

}
