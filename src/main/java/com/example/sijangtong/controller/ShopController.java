package com.example.sijangtong.controller;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.repository.StoreRepository;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;
import com.example.sijangtong.service.ProductService;
import com.example.sijangtong.service.StoreService;
import com.example.sijangtong.service.StoreServiceImpl;

import groovyjarjarpicocli.CommandLine.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/shop")
@Log4j2
@RequiredArgsConstructor
public class ShopController {

    private final StoreService service;
    private final ProductService productService;

    @GetMapping("/storeDetail")
    public void getDetail(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, @Parameters Long storeId,
            Model model,
            RedirectAttributes rttr) {
        log.info("디테일 폼 요청");
        model.addAttribute("result", productService.getProductList(pageRequestDto, storeId));
        model.addAttribute("storeId", storeId);

    }

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {

        log.info("리스트 폼 요청");
        model.addAttribute("result", service.getStoreList(pageRequestDto));
    }

    @GetMapping("/home")
    public void getHome(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {

        log.info("홈 요청");
    }

    @GetMapping("/read")
    public void getread(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("리스트 폼 요청");
    }

    @GetMapping("/buyitem")
    public void getbuyItem(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, @Parameters Long productId,
            Model model) {

        log.info("구매 폼 요청");
        model.addAttribute("result", productService.getProductRow(productId));
    }

    @GetMapping("/contact")
    public void getContact(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("문의 사항 폼 요청");
    }

    @GetMapping("/buyitemlist")
    public void getBuyItemList(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("장바구니 폼 요청");
    }

    @GetMapping("/cart")
    public void getCart(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("구매 폼 요청");
    }

}
