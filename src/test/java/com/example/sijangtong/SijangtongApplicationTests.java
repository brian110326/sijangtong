package com.example.sijangtong;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sijangtong.constant.MemberRole;
import com.example.sijangtong.constant.StoreCategory;
import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.repository.MemberRepository;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;

@SpringBootTest
class SijangtongApplicationTests {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductImgRepository productImgRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {

	}

}
