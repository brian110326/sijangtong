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
        // WHERE p.PRODUCT_ID = 199;

        JPQLQuery<Review> query = from(review);
        query.leftJoin(product).on(review.product.eq(product));

        JPQLQuery<Tuple> tuple = query.select(product, review).where(review.product.eq(product));

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

}
