package com.example.sijangtong.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sijangtong.dto.PageRequestDto;
import com.example.sijangtong.dto.PageResultDto;
import com.example.sijangtong.dto.ProductDto;
import com.example.sijangtong.dto.StoreDto;
import com.example.sijangtong.entity.OrderItem;
import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.Review;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.example.sijangtong.repository.OrderItemRepository;
import com.example.sijangtong.repository.ProductImgRepository;
import com.example.sijangtong.repository.ProductRepository;
import com.example.sijangtong.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductImgRepository productImgRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<ProductDto, Object[]> getProductList(PageRequestDto pageRequestDto, Long storeId) {
        Page<Object[]> result = productImgRepository.getProductList(pageRequestDto.getPageable(Sort.by("productId")),
                storeId);

        Function<Object[], ProductDto> fn = (en -> entityToDto((Product) en[0],
                (List<ProductImg>) Arrays.asList((ProductImg) en[1])));

        return new PageResultDto<>(result, fn);

    }

    @Override
    public ProductDto getProductRow(Long productId) {
        List<Object[]> result = productImgRepository.getProductRow(productId);

        Product product = (Product) result.get(0)[0];

        List<ProductImg> list = result.stream().map(en -> (ProductImg) en[1]).collect(Collectors.toList());

        return entityToDto(product, list);
    }

    @Override
    public Long removeProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        List<OrderItem> orderItems = orderItemRepository.findByProduct(product);

        productImgRepository.deleteByProduct(product);

        // product 삭제 시 orderItem안 product항목만 null로 설정
        orderItems.forEach(orderItem -> orderItem.setProduct(null));
        reviewRepository.deleteByProduct(product);

        productRepository.delete(product);

        // ex) return 값이 Long 이유 : 200번 삭제성공 alert창(예시)
        return product.getProductId();
    }

}
