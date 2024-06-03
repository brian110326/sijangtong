package com.example.sijangtong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.StoreRepository;
import com.example.sijangtong.service.StoreService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    StoreService storeService;

    @GetMapping("/storeCategory")
    public ResponseEntity<List<Store>> getStoreCategory(@RequestParam StoreCategory storeCategory) {
        List<Store> categorys = storeService.findStoreCategory(storeCategory);

        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

}
