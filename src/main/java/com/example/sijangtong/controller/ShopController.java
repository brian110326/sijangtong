package com.example.sijangtong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/shop")
@Log4j2
public class ShopController {
    


    @GetMapping("/storeDetail")
    public void getDetail() {
        log.info("디테일 폼 요청");
    }
    
    @GetMapping("/list")
    public void getList() {
        log.info("리스트 폼 요청");
    }
    
    @GetMapping("/read")
    public void getread() {
        log.info("리스트 폼 요청");
    }
    
    @GetMapping("/buyitem")
    public void getbuyItem() {
        log.info("구매 폼 요청");
    }
}
