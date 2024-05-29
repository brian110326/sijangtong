package com.example.sijangtong.controller;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.repository.StoreRepository;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;
import com.example.sijangtong.service.ProductService;
import com.example.sijangtong.service.StoreService;
import com.example.sijangtong.service.StoreServiceImpl;
import groovyjarjarpicocli.CommandLine.Parameters;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shop")
@Log4j2
@RequiredArgsConstructor
public class ShopController {

  private final StoreService service;
  private final ProductService productService;

  // 상품 리스트
  @GetMapping("/storeDetail")
  public void getDetail(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      Model model,
      RedirectAttributes rttr) {
    log.info("디테일 폼 요청");
    model.addAttribute(
        "result",
        productService.getProductList(pageRequestDto, storeId));
    model.addAttribute("storeId", storeId);
  }

  // 스토어 리스트
  @GetMapping("/list")
  public void getList(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Model model) {
    log.info("리스트 폼 요청");
    model.addAttribute("result", service.getStoreList(pageRequestDto));
  }

  @GetMapping("/home")
  public void getHome(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Model model) {
    Long storeId = StoreId();
    log.info("홈 요청", productService.getProductList(pageRequestDto, storeId));

    model.addAttribute(
        "result",
        productService.getProductList(pageRequestDto, storeId));
  }

  // @GetMapping("/read")
  // public void getread(
  // @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
  // log.info("설명 폼 요청");
  // }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/buyitem")
  public void getbuyItem(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long productId,
      Model model) {
    ProductDto productDto = productService.getProductRow(productId);
    log.info("구매 폼 요청 productDto :{}", productDto);
    model.addAttribute("result", productDto);
    model.addAttribute("requestDto", pageRequestDto);
  }

  @GetMapping("/read")
  public void getread(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      Model model) {
    log.info("설명 폼 요청");
    StoreDto storeDto = service.getRow(storeId);

    model.addAttribute("storeDto", storeDto);

    List<String> districts = Arrays.asList(
        "강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파",
        "양천", "영등포", "용산", "은평", "종로", "중구", "중랑");

    model.addAttribute("districts", districts);
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @GetMapping("/modify")
  public void getModify(StoreDto updateStoreDto,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      Model model) {
    log.info("설명 폼 요청");
    StoreDto storeDto = service.getRow(storeId);

    model.addAttribute("storeDto", storeDto);

    List<String> districts = Arrays.asList(
        "강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파",
        "양천", "영등포", "용산", "은평", "종로", "중구", "중랑");

    model.addAttribute("districts", districts);
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/modify")
  public String postStoreUpdate(@Valid StoreDto updateStoreDto, BindingResult result,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      RedirectAttributes rttr, Model model) {

    if (result.hasErrors()) {

      List<String> districts = Arrays.asList(
          "강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파",
          "양천", "영등포", "용산", "은평", "종로", "중구", "중랑");

      model.addAttribute("districts", districts);
      return "/shop/modify";
    }

    try {
      Long updatedStoreId = service.storeUpdate(updateStoreDto);
    } catch (Exception e) {
      e.printStackTrace();
      rttr.addFlashAttribute("error", e.getMessage());
      return "redirect:/shop/modify";
    }

    rttr.addAttribute("storeId", updateStoreDto.getStoreId());
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/read";
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @GetMapping("/pModify")
  public void getPModify(ProductDto updateProductDto, @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long productId,
      @Parameters Long storeId,
      Model model) {
    log.info("product 수정 페이지 요청");
    ProductDto productDto = productService.getProductRow(productId);
    model.addAttribute("productDto", productDto);
    model.addAttribute("productId", productId);
    model.addAttribute("storeId", storeId);
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/pModify")
  public String postProductUpdate(@Valid ProductDto updateProductDto, BindingResult result,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      RedirectAttributes rttr, Model model) {

    log.info("pageRequestDto : {}", pageRequestDto);
    log.info("updateProductDto : {}", updateProductDto);

    if (result.hasErrors()) {
      model.addAttribute("storeId", storeId);
      return "/shop/pModify";
    }

    Long updatedProductId = null;

    try {
      updatedProductId = productService.productUpdate(updateProductDto);
    } catch (Exception e) {
      e.printStackTrace();
      rttr.addFlashAttribute("error", e.getMessage());
      return "redirect:/shop/pModify";
    }

    rttr.addFlashAttribute("newPMsg", updatedProductId);

    rttr.addAttribute("storeId", storeId);
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/storeDetail";

  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/remove")
  public String postStoreRemove(
      Long storeId,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      RedirectAttributes rttr) {

    log.info("storeId!!!!!!!!!!!!1 {}", storeId);

    Long removedStoreId = service.removeStore(storeId);

    rttr.addFlashAttribute("msg", removedStoreId);

    rttr.addFlashAttribute("msg", removedStoreId);

    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/list";
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/pRemove")
  public String postProductRemove(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Long productId,
      Long storeId,
      RedirectAttributes rttr) {
    productService.removeProduct(productId);

    rttr.addAttribute("storeId", storeId);
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());
    return "redirect:/shop/storeDetail";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/buyitemlist")
  public void getBuyItemList(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
    log.info("장바구니 폼 요청");
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/cart")
  public void getCart(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
    log.info("구매 폼 요청");
  }

  // 리스트에 무작위로 뿌리는 추천 상품을 위한 sorid 뽑기
  private long StoreId() {
    return new Double(((Math.random() * 198) + 1)).longValue();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/insert")
  public void insertStore(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
    log.info("스토어 생성 폼 요청");
  }
}
