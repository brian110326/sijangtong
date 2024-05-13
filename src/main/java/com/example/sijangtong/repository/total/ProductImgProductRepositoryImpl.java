package com.example.sijangtong.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.ProductImg;
import com.example.sijangtong.entity.QProduct;
import com.example.sijangtong.entity.QProductImg;
import com.example.sijangtong.entity.QStore;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class ProductImgProductRepositoryImpl extends QuerydslRepositorySupport implements ProductImgProductRepository {

    public ProductImgProductRepositoryImpl() {
        super(ProductImg.class);

    }

    @Override
    public Page<Object[]> getProductList(Pageable pageable, Long storeId) {

        // SELECT pi.*,p.*
        // FROM PRODUCT_IMG pi
        // LEFT JOIN PRODUCT p on pi.PRODUCT_PRODUCT_ID = p.PRODUCT_ID
        // WHERE pi.IMG_ID IN
        // (SELECT MIN(pi2.img_id) FROM PRODUCT_IMG pi2 GROUP BY pi2.product_product_id)
        // AND p.STORE_STORE_ID = 44;

        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;
        QStore store = QStore.store;

        JPQLQuery<ProductImg> query = from(productImg);

        query.leftJoin(product).on(productImg.product.eq(product));

        JPQLQuery<Tuple> tuple = query.select(product, productImg, store)
                .where(productImg.imgId.in(
                        JPAExpressions.select(productImg.imgId.min()).from(productImg).groupBy(productImg.product))
                        .and(product.store.storeId.eq(storeId)));

        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

    @Override
    public List<Object[]> getProductRow(Long productId) {

        // SELECT pi.*,p.*
        // FROM PRODUCT_IMG pi
        // LEFT JOIN PRODUCT p on pi.PRODUCT_PRODUCT_ID = p.PRODUCT_ID
        // WHERE p.PRODUCT_ID = 44 AND p.STORE_STORE_ID = 44;

        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;

        JPQLQuery<ProductImg> query = from(productImg);

        query.leftJoin(product).on(productImg.product.eq(product));

        JPQLQuery<Tuple> tuple = query.select(product, productImg)
                .where(product.productId.eq(productId));

        List<Tuple> result = tuple.fetch();

        return result.stream().map(t -> t.toArray()).collect(Collectors.toList());

    }

}
