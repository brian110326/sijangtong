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

	// @Test
	// @DisplayName("아이템 더미 데이터 삽입")
	// public void productInset(){
	// IntStream.rangeClosed(1, 200).forEach(i->{
	// Product.builder()
	// .pName("상품"+i)
	// .amount(i)
	// .price((int)(Math.random()*40000)+1)
	// .store(

	// ).build();
	// });
	// }

	@Test
	public void memberInsert() {
		IntStream.rangeClosed(1, 200).forEach((i -> {
			Member member = Member.builder()
					.memberEmail("mem" + i + "@naver.com")
					.memberNickname("닉네임" + i)
					.memberAddress("서울" + i)
					.memberPwd("1111")
					.memberRole(MemberRole.MEMBER).build();

			memberRepository.save(member);

		}));

	}

	@Test
	public void inseetInfo(){
		IntStream.rangeClosed(0, 0)
	}

	// @Test
	// public void storeInsert(){
	// IntStream.rangeClosed(1, 200).forEach(i->{
	// Store store = Store.builder()
	// .storeCategory(StoreCategory.SEAFOOD).build();
	// });
	// }

	// @Test
	// @DisplayName("주문 아이템 더미 데이터 삽입")
	// public void orderItemInsert(){
	// IntStream.rangeClosed(1, 200).forEach(i ->{
	// OrderItem.builder()
	// .product("상품"+i).build();
	// });

	// }

}
