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

public class ReviewStoreProductOrderRepositoryImpl extends QuerydslRepositorySupport
        implements ReviewStoreProductOrderRepository {

    public ReviewStoreProductOrderRepositoryImpl() {
        super(Review.class);

    }

    @Override
    public Page<Object[]> getReviewList(Pageable pageable, Long storeId) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QProduct product = QProduct.product;
        QOrder order = QOrder.order;

        // SELECT s.*,p.*,r.*, o.*
        // FROM REVIEW r
        // LEFT JOIN STORE s ON r.STORE_STORE_ID = s.STORE_ID
        // LEFT JOIN PRODUCT p ON r.STORE_STORE_ID = p.STORE_STORE_ID
        // LEFT JOIN ORDERS o ON o.ORDER_ID = r.ORDER_ORDER_ID
        // WHERE s.STORE_ID = 199;

        JPQLQuery<Review> query = from(review);
        query.leftJoin(store).on(review.store.eq(store));
        query.leftJoin(product).on(review.store.eq(product.store));
        query.leftJoin(order).on(review.order.eq(order));

        JPQLQuery<Tuple> tuple = query.select(store, product, review, order).where(store.storeId.eq(storeId));

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

}
