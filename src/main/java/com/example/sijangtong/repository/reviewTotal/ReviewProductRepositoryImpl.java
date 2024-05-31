package com.example.sijangtong.repository.reviewTotal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.QOrder;
import com.example.sijangtong.entity.QProduct;
import com.example.sijangtong.entity.QReview;
import com.example.sijangtong.entity.QStore;
import com.example.sijangtong.entity.Review;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReviewProductRepositoryImpl extends QuerydslRepositorySupport
                implements ReviewProductRepository {

        public ReviewProductRepositoryImpl() {
                super(Review.class);

        }

        @Override
        public Page<Object[]> getReviewList(Pageable pageable, Long productId) {
                QReview review = QReview.review;
                QProduct product = QProduct.product;

                // SELECT *
                // FROM REVIEW r
                // LEFT JOIN PRODUCT p ON r.PRODUCT_PRODUCT_ID = p.PRODUCT_ID
                // WHERE p.PRODUCT_ID = :productId; // 변경된 부분

                JPQLQuery<Review> query = from(review);
                query.leftJoin(product).on(review.product.eq(product));

                JPQLQuery<Tuple> tuple = query.select(product, review).where(review.product.productId.eq(productId));

                // 페이지 처리
                tuple.offset(pageable.getOffset());
                tuple.limit(pageable.getPageSize());

                List<Tuple> result = tuple.fetch();
                log.info("tuple 데이터 {}", result);
                long count = tuple.fetchCount();

                return new PageImpl<>(result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable,
                                count);
        }

}
