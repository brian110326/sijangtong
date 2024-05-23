package com.example.sijangtong.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/shop")
@Log4j2
@RequiredArgsConstructor
public class ShopController {

    private final StoreService service;
    private final ProductService productService;

    // 상품 리스트
    @GetMapping("/storeDetail")
    public void getDetail(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, @Parameters Long storeId,
            Model model,
            RedirectAttributes rttr) {
        log.info("디테일 폼 요청");
        model.addAttribute("result", productService.getProductList(pageRequestDto, storeId));
        model.addAttribute("storeId", storeId);

    }

    // 스토어 리스트
    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {

        log.info("리스트 폼 요청");
        model.addAttribute("result", service.getStoreList(pageRequestDto));
    }

    @GetMapping("/home")
    public void getHome(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {
        Long storeId = StoreId();
        log.info("홈 요청", productService.getProductList(pageRequestDto, storeId));

        model.addAttribute("result", productService.getProductList(pageRequestDto, storeId));
    }

    @GetMapping({ "/read", "/modify" })
    public void getread(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, @Parameters Long storeId,
            Model model) {
        log.info("설명 폼 요청");
        StoreDto storeDto = service.getRow(storeId);

        model.addAttribute("storeDto", storeDto);

        List<String> districts = Arrays.asList("강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작",
                "마포", "서대문", "서초", "성동", "성북", "송파", "양천", "영등포", "용산", "은평", "종로", "중구", "중랑");
        model.addAttribute("districts", districts);

    }

    @PostMapping("/remove")
    public String postStoreRemove(Long storeId, @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
            RedirectAttributes rttr) {
        Long removedStoreId = service.removeStore(storeId);

        rttr.addFlashAttribute("msg", removedStoreId);

        rttr.addAttribute("page", pageRequestDto.getPage());
        rttr.addAttribute("type", pageRequestDto.getType());
        rttr.addAttribute("keyword", pageRequestDto.getKeyword());

        return "redirect:/shop/list";

    }

    @PostMapping("/pRemove")
    public String postProductRemove(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Long productId,
            Long storeId, RedirectAttributes rttr) {

        productService.removeProduct(productId);

        rttr.addAttribute("storeId", storeId);
        rttr.addAttribute("page", pageRequestDto.getPage());
        rttr.addAttribute("type", pageRequestDto.getType());
        rttr.addAttribute("keyword", pageRequestDto.getKeyword());
        return "redirect:/shop/storeDetail";

    }

    @PostMapping("/modify")
    public String postStoreUpdate(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, StoreDto updateStoreDto,
            @Parameters Long storeId, RedirectAttributes rttr) {
        Long updatedStoreId = service.storeUpdate(updateStoreDto);

        rttr.addAttribute("storeId", updateStoreDto.getStoreId());
        rttr.addAttribute("page", pageRequestDto.getPage());
        rttr.addAttribute("type", pageRequestDto.getType());
        rttr.addAttribute("keyword", pageRequestDto.getKeyword());
        return "redirect:/shop/read";

    }

    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/buyitemlist")
    public void getBuyItemList(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("장바구니 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public void getCart(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("구매 폼 요청");
    }

    // 리스트에 무작위로 뿌리는 추천 상품을 위한 sorid 뽑기
    private long StoreId() {
        return new Double(((Math.random() * 198) + 1)).longValue();
    }

}
