package com.example.sijangtong.controller;

import com.example.sijangtong.dto.OrderItemDto;
import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.ReviewDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.repository.StoreRepository;
import com.example.sijangtong.repository.total.StoreImgStoreRepository;
import com.example.sijangtong.service.EmailService;
import com.example.sijangtong.service.EmailServiceImpl;
import com.example.sijangtong.service.OrderItemService;
import com.example.sijangtong.service.OrderService;
import com.example.sijangtong.service.ProductService;
import com.example.sijangtong.service.ReviewService;
import com.example.sijangtong.service.StoreService;
import com.example.sijangtong.service.StoreServiceImpl;
import groovyjarjarpicocli.CommandLine.Parameters;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shop")
@Log4j2
@RequiredArgsConstructor
public class ShopController {

  private final StoreService service;
  private final ProductService productService;
  private final OrderItemService orderItemService;
  private final OrderService orderService;
  private final ReviewService reviewService;
  private final EmailService emailService;

  // 상품 리스트
  @GetMapping("/storeproducts")
  public void getDetail(
      @RequestParam(required = false, value = "orderItemCount") int orderItemCount,
      @ModelAttribute("requestDto") PageRequestDto requestDto,
      Long storeId,
      Model model,
      RedirectAttributes rttr) {
    log.info("디테일 폼 요청 {}", storeId);
    StoreDto storeDto = service.getRow(storeId);
    if (orderItemCount <= 0) {
    }
    model.addAttribute(
        "result",
        productService.getProductList(requestDto, storeId));
    model.addAttribute("storeId", storeId);
    model.addAttribute("storeDto", storeDto);
    model.addAttribute("orderItemCount", orderItemCount);
  }

  // 스토어 리스트
  @GetMapping("/list")
  public void getList(
      @ModelAttribute("requestDto") PageRequestDto requestDto,
      String memberEmail,
      Model model) {
    List<OrderItemDto> orderItemDtos = orderItemService.getMemberOrderItems(
        memberEmail);
    if (!orderItemDtos.isEmpty()) {
      int orderItemCount = orderItemDtos.size();
      model.addAttribute("orderItemCount", orderItemCount);
    }
    PageResultDto<StoreDto, Object[]> pageResultDto = service.getStoreList(
        requestDto);
    log.info("리스트 폼 요청 {}, {}", orderItemDtos, pageResultDto);
    model.addAttribute("result", pageResultDto);
  }

  @GetMapping("/home")
  public void getHome(
      @ModelAttribute("requestDto") PageRequestDto requestDto,
      Model model) {
    Long storeId = StoreId();
    log.info("홈 요청", productService.getProductList(requestDto, storeId));

    model.addAttribute(
        "result",
        productService.getProductList(requestDto, storeId));
  }

  // @GetMapping("/read")
  // public void getread(
  // @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
  // log.info("설명 폼 요청");
  // }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/buyitem")
  public void getbuyItem(
      @ModelAttribute("requestDto") PageRequestDto requestDto,
      @Parameters Long productId,
      @Parameters Long storeId,
      Model model) {
    log.info("구매 폼 요청");
    model.addAttribute("result", productService.getProductRow(productId));
    model.addAttribute("requestDto", requestDto);
    model.addAttribute("storeId", storeId);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/buyitem")
  public String PostbuyItem(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Long storeId,
      Long productId,
      int amount,
      String memberEmail,
      RedirectAttributes rttr) {
    log.info(
        "구매 post 폼 요청 {} {} {}  {}",
        productId,
        amount,
        memberEmail,
        storeId);
    Long orderId = orderItemService.createOrderItem(
        amount,
        productId,
        memberEmail,
        storeId);
    List<OrderItemDto> orderItemDtos = orderItemService.getMemberOrderItems(
        memberEmail);
    if (!orderItemDtos.isEmpty()) {
      int orderItemCount = orderItemDtos.size();
      rttr.addAttribute("orderItemCount", orderItemCount);
    }
    rttr.addAttribute("orderId", orderId);
    rttr.addAttribute("storeId", storeId);
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());
    rttr.addAttribute("type", pageRequestDto.getType());
    return "redirect:/shop/storeproducts";
  }

  @GetMapping({ "/read" })
  public void getread(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      Model model) {
    log.info("설명 폼 요청");
    StoreDto storeDto = service.getRow(storeId);

    model.addAttribute("storeDto", storeDto);

    List<String> districts = Arrays.asList(
        "강남",
        "강동",
        "강북",
        "강서",
        "관악",
        "광진",
        "구로",
        "금천",
        "노원",
        "도봉",
        "동대문",
        "동작",
        "마포",
        "서대문",
        "서초",
        "성동",
        "성북",
        "송파",
        "양천",
        "영등포",
        "용산",
        "은평",
        "종로",
        "중구",
        "중랑");

    model.addAttribute("districts", districts);
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @GetMapping("/modify")
  public void getModify(
      StoreDto updateStoreDto,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      Model model) {
    log.info("설명 폼 요청");
    StoreDto storeDto = service.getRow(storeId);

    model.addAttribute("storeDto", storeDto);
    model.addAttribute("storeId", storeId);

    List<String> districts = Arrays.asList(
        "강남",
        "강동",
        "강북",
        "강서",
        "관악",
        "광진",
        "구로",
        "금천",
        "노원",
        "도봉",
        "동대문",
        "동작",
        "마포",
        "서대문",
        "서초",
        "성동",
        "성북",
        "송파",
        "양천",
        "영등포",
        "용산",
        "은평",
        "종로",
        "중구",
        "중랑");

    model.addAttribute("districts", districts);

    // modal 작업용
    PageResultDto<ProductDto, Object[]> pList = productService.getProductList(
        pageRequestDto,
        storeId);
    model.addAttribute("pList", pList);
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/modify")
  public String postStoreUpdate(
      @Valid StoreDto updateStoreDto,
      BindingResult result,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      RedirectAttributes rttr,
      Model model) {
    if (result.hasErrors()) {
      List<String> districts = Arrays.asList(
          "강남",
          "강동",
          "강북",
          "강서",
          "관악",
          "광진",
          "구로",
          "금천",
          "노원",
          "도봉",
          "동대문",
          "동작",
          "마포",
          "서대문",
          "서초",
          "성동",
          "성북",
          "송파",
          "양천",
          "영등포",
          "용산",
          "은평",
          "종로",
          "중구",
          "중랑");

      model.addAttribute("districts", districts);
      return "/shop/modify";
    }

    try {
      Long updatedStoreId = service.storeUpdate(updateStoreDto);
      log.info("UPDATEDTORE ID : ", updatedStoreId);
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
  public void getPModify(
      ProductDto updateProductDto,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
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
  public String postProductUpdate(
      @Valid ProductDto updateProductDto,
      BindingResult result,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @Parameters Long storeId,
      RedirectAttributes rttr,
      Model model) {
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

    return "redirect:/shop/storeproducts";
  }

  @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
  @PostMapping("/remove")
  public String postStoreRemove(
      Long storeId,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      RedirectAttributes rttr) {
    Long removedStoreId = service.removeStore(storeId);

    log.info("storeId!!!!!!!!!!!!1 {}", storeId);

    rttr.addFlashAttribute("msg", removedStoreId);

    rttr.addFlashAttribute("msg", removedStoreId);

    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/list";
  }

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
    return "redirect:/shop/storeproducts";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/buyitemlist")
  public void getBuyItemList(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @RequestParam(required = false, value = "memberEmail") String memberEmail,
      Model model) {
    List<OrderItemDto> orderItemDtos = orderItemService.getMemberOrderItems(
        memberEmail);
    log.info("장바구니 폼 요청 {}", orderItemDtos);

    if (!orderItemDtos.isEmpty()) {
      int orderItemCount = orderItemDtos.size();
      model.addAttribute("orderItemCount", orderItemCount);
    }
    model.addAttribute("orderItemDtos", orderItemDtos);
    model.addAttribute("memberEmail", memberEmail);
  }

  @PostMapping("/removeProductitem")
  public String postRemoveProductitem(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      Long orderItemId,
      String memberEmail,
      RedirectAttributes rttr) {
    log.info("장바구니 삭제 폼 ");
    orderItemService.deleteOrderItem(orderItemId);
    rttr.addAttribute("memberEmail", memberEmail);
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/buyitemlist";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/cart")
  public void getCart(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      String memberEmail,
      Model model) {
    List<OrderItemDto> orderItemDtos = orderItemService.getMemberOrderItems(
        memberEmail);
    if (!orderItemDtos.isEmpty()) {
      int orderItemCount = orderItemDtos.size();
      model.addAttribute("orderItemCount", orderItemCount);
    }
    model.addAttribute("orderItemDtos", orderItemDtos);
    model.addAttribute("memberEmail", memberEmail);
    log.info("구매 폼 요청{}", orderItemDtos);
  }

  @PostMapping("/cart")
  public String postCart(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      String memberEmail,
      String payment,
      RedirectAttributes rttr) {
    Long orderId = orderService.orderfinish(memberEmail, payment);
    rttr.addAttribute("orderId", orderId);
    return "redirect:/member/profile";
  }

  // 리스트에 무작위로 뿌리는 추천 상품을 위한 sorid 뽑기
  private long StoreId() {
    return new Double(((Math.random() * 198) + 1)).longValue();
  }

  @GetMapping("/contact")
  public void getContact(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      @RequestParam(required = false, value = "orderItemCount") Long orderItemCount,
      Model model) {
    log.info("홈 요청 {} ", orderItemCount);

    model.addAttribute("orderItemCount", orderItemCount);
    model.addAttribute("requestDto", pageRequestDto);
  }

  @PostMapping("/contact")
  public String PostContact(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      String title, String name, String content, String email,
      RedirectAttributes rttr) {
    emailService.sendMail(name, title, content, email);
    log.info("건의사항 post 폼 요청 ");
    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());
    rttr.addAttribute("type", pageRequestDto.getType());
    return "redirect:/shop/home";
  }

  @GetMapping("sInsert")
  public void insertStore(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      StoreDto storeDto) {
    log.info("스토어 생성 폼 요청");
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/sInsert")
  public String storeInsert(
      @Valid StoreDto storeDto,
      BindingResult result,
      Model model,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      RedirectAttributes rttr) {
    log.info("스토어 생성 {}", storeDto);

    if (result.hasErrors()) {
      return "/shop/insert";
    }

    // 서비스 호출
    Long storeId = service.storeInsert(storeDto);

    rttr.addFlashAttribute("msg", storeId);

    rttr.addAttribute("page", pageRequestDto.getPage());
    rttr.addAttribute("type", pageRequestDto.getType());
    rttr.addAttribute("keyword", pageRequestDto.getKeyword());

    return "redirect:/shop/list";
    // ${#authentication.principal.memberDto.memberNickname}
  }

  @PreAuthorize("authentication.principal.memberDto.storeId == #productDto.storeId")
  @GetMapping("/pInsert")
  public void insertProduct(
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      ProductDto productDto) {
    log.info("프로덕트 생성 폼 요청");
  }

  @PostMapping("/pInsert")
  public String insertproduct(
      @Valid ProductDto productDto,
      BindingResult result,
      Model model,
      @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
      RedirectAttributes rttr) {
    log.info("프로덕트 생성 {}", productDto);

    if (result.hasErrors()) {
      return "/shop/pInsert";
    }

    Long productId = productService.productInsert(productDto);

    rttr.addFlashAttribute("msg", productDto.getStoreId());
    rttr.addAttribute("storeId", productDto.getStoreId());
    rttr.addAttribute("page", 1);
    rttr.addAttribute("type", "");
    rttr.addAttribute("keyword", "");

    return "redirect:/shop/storeproducts";
  }
}
