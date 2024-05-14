package com.example.sijangtong.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.QReview;
import com.example.sijangtong.entity.QStore;
import com.example.sijangtong.entity.QStoreImg;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class StoreImgStoreRepositoryImpl extends QuerydslRepositorySupport implements StoreImgStoreRepository {

    public StoreImgStoreRepositoryImpl() {
        super(StoreImg.class);

    }

    @Override
    public Page<Object[]> getTotalList(String type, String keyword, Pageable pageable) {

        QStoreImg storeImg = QStoreImg.storeImg;
        QStore store = QStore.store;
        QReview review = QReview.review;
        
        JPQLQuery<StoreImg> query = from(storeImg);
        query.leftJoin(store).on(storeImg.store.eq(store));

        JPQLQuery<Tuple> tuple = query
                .select(store, storeImg,
                        (JPAExpressions.select(review.grade.avg()).from(review).where(review.store.eq(store))))
                .where(storeImg.storeImgId.in(JPAExpressions.select(storeImg.storeImgId.min()).from(storeImg)
                        .groupBy(storeImg.store)));

        // 검색 조건
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(store.storeId.gt(0L));

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("t")) {
            conditionBuilder.or(store.storeName.contains(keyword));
        }

        builder.and(conditionBuilder);
        tuple.where(builder);

        // 페이지 나누기
        // sort 지정
        // 정렬기준이 1개가 아니라 기준이 계속 변경될 때 대비용
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            // 어떤 클래스를 기준으로 sort할것인지
            PathBuilder<Store> orderByExpression = new PathBuilder<>(Store.class, "store");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result =  tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

    @Override
    public List<Object[]> getStoreRow(Long storeId) {

        // SELECT s.STORE_ID ,s.STORE_NAME ,si.ST_PATH ,si.ST_UUID ,si.ST_IMG_NAME,
        // (SELECT AVG(r.grade) FROM REVIEW r WHERE r.store_store_id = s.STORE_ID) AS
        // grade_avg
        // FROM STORE_IMG si
        // LEFT JOIN STORE s ON si.STORE_STORE_ID = s.STORE_ID
        // WHERE s.STORE_ID = 282;

        QStoreImg storeImg = QStoreImg.storeImg;
        QStore store = QStore.store;
        QReview review = QReview.review;

        JPQLQuery<StoreImg> query = from(storeImg);
        query.leftJoin(store).on(storeImg.store.eq(store));

        JPQLQuery<Tuple> tuple = query
                .select(store, storeImg,
                        (JPAExpressions.select(review.grade.avg()).from(review).where(review.store.eq(store))))
                .where(store.storeId.eq(storeId));

        List<Tuple> result = tuple.fetch();

        return result.stream().map(t -> t.toArray()).collect(Collectors.toList());

    }

}
