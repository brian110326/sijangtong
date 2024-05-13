package com.example.sijangtong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
// @RequestMapping("")
public class HomeController {
    
    @GetMapping( value = {"/index"})
    public void getMethodName() {
        log.info("기본 화면 요청");
        
    }
    
}
