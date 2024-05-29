package com.example.sijangtong.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.Product;
import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.QProduct;
import com.example.sijangtong.entity.QProductImg;
import com.example.sijangtong.entity.QReview;
import com.example.sijangtong.entity.QStore;
import com.example.sijangtong.entity.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class ProductImgProductRepositoryImpl extends QuerydslRepositorySupport implements ProductImgProductRepository {

    public ProductImgProductRepositoryImpl() {
        super(ProductImg.class);

    }

    @Override
    public Page<Object[]> getProductList(String type, String keyword, Pageable pageable, Long storeId) {

        // SELECT pi.*,p.*, (SELECT AVG(r.grade) FROM REVIEW r WHERE
        // r.product_product_id = p.PRODUCT_ID) AS grade
        // FROM PRODUCT_IMG pi
        // LEFT JOIN PRODUCT p on pi.PRODUCT_PRODUCT_ID = p.PRODUCT_ID
        // WHERE pi.IMG_ID IN
        // (SELECT MIN(pi2.img_id) FROM PRODUCT_IMG pi2 GROUP BY pi2.product_product_id)
        // AND p.STORE_STORE_ID = 44;

        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;
        QStore store = QStore.store;
        QReview review = QReview.review;

        JPQLQuery<ProductImg> query = from(productImg);

        query.leftJoin(product).on(productImg.product.eq(product));

        JPQLQuery<Tuple> tuple = query
                .select(product, productImg, store,
                        (JPAExpressions.select(review.grade.avg()).from(review).where(review.product.eq(product))))
                .where(productImg.imgId.in(
                        JPAExpressions.select(productImg.imgId.min()).from(productImg).groupBy(productImg.product))
                        .and(product.store.storeId.eq(storeId)));

        // 검색 조건
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(product.productId.gt(0L));

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        // pn : productname
        if (type.equals("pn")) {
            conditionBuilder.or(product.pName.contains(keyword));
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
            PathBuilder<Product> orderByExpression = new PathBuilder<>(Product.class, "product");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

    @Override
    public List<Object[]> getProductRow(Long productId) {

        // SELECT pi.*,p.*, (SELECT AVG(r.grade) FROM REVIEW r WHERE
        // r.product_product_id = p.PRODUCT_ID) AS grade
        // FROM PRODUCT_IMG pi
        // LEFT JOIN PRODUCT p on pi.PRODUCT_PRODUCT_ID = p.PRODUCT_ID
        // WHERE p.PRODUCT_ID = 44 AND p.STORE_STORE_ID = 44;

        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;
        QReview review = QReview.review;
        QStore store = QStore.store;

        JPQLQuery<ProductImg> query = from(productImg);

        query.leftJoin(product).on(productImg.product.eq(product));

        JPQLQuery<Tuple> tuple = query
                .select(product, productImg, store,
                        (JPAExpressions.select(review.grade.avg()).from(review).where(review.product.eq(product))))
                .where(product.productId.eq(productId));
        log.info("물품 구메 (product, review ,avg)tuple  :{}", tuple);

        List<Tuple> result = tuple.fetch();

        log.info("(product, review ,avg)result  :{}", result);
        return result.stream().map(t -> t.toArray()).collect(Collectors.toList());

    }

}
