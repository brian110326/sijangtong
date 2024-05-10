package com.example.sijangtong.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.QStore;
import com.example.sijangtong.entity.QStoreImg;
import com.example.sijangtong.entity.Store;
import com.example.sijangtong.entity.StoreImg;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class StoreImgStoreRepositoryImpl extends QuerydslRepositorySupport implements StoreImgStoreRepository {

    public StoreImgStoreRepositoryImpl() {
        super(StoreImg.class);

    }

    @Override
    public Page<Object[]> getTotalList(Pageable pageable) {

        QStoreImg storeImg = QStoreImg.storeImg;
        QStore store = QStore.store;

        // SELECT s.STORE_ID ,s.STORE_NAME ,si.ST_PATH ,si.ST_UUID ,si.ST_IMG_NAME
        // FROM STORE_IMG si
        // LEFT JOIN STORE s ON si.STORE_STORE_ID = s.STORE_ID
        // WHERE si.STORE_IMG_ID IN (SELECT MIN(si2.store_img_id) FROM STORE_IMG si2
        // GROUP BY si2.STORE_STORE_ID);

        JPQLQuery<StoreImg> query = from(storeImg);
        query.leftJoin(store).on(storeImg.store.eq(store));

        JPQLQuery<Tuple> tuple = query
                .select(store, storeImg)
                .where(storeImg.storeImgId.in(JPAExpressions.select(storeImg.storeImgId.min()).from(storeImg)
                        .groupBy(storeImg.store)));

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

}
