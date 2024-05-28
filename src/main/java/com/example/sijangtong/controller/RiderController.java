package com.example.sijangtong.controller;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.RiderDto;
import com.example.sijangtong.entity.Rider;
import com.example.sijangtong.service.RiderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/rider")
public class RiderController {

  private final RiderServiceImpl riderService;

  @GetMapping("/riderList")
  public void riderList(
    @ModelAttribute("requestDto") PageRequestDto pageRequestDto,
    Model model
  ) {
    log.info("라이더 리스트 요청 {}");

    PageResultDto<RiderDto, Rider> result = riderService.getRiderList(
      pageRequestDto
    );

    model.addAttribute("result", result);
  }
}
