package com.example.sijangtong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.dto.StoreImgDto;
import com.example.sijangtong.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/store")
public class RemoveRestController {

    private final StoreService storeService;

    @GetMapping("/{storeId}/storeImages")
    public ResponseEntity<List<StoreImgDto>> getStore(@PathVariable("storeId") Long storeId) {

        log.info("storeId ==> {}", storeId);

        StoreDto storeDto = storeService.getRow(storeId);
        List<StoreImgDto> imgList = storeDto.getStoreImgDtos();

        return new ResponseEntity<List<StoreImgDto>>(imgList, HttpStatus.OK);
    }

}
