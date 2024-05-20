package com.example.sijangtong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
// @RequestMapping("")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String getHome(RedirectAttributes rttr, @ModelAttribute("requestDto") PageRequestDto requestDto) {

        log.info("기본 화면 요청");

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/shop/home";
    }

}
