package com.example.sijangtong.repository.orderTotal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.Order;
import com.example.sijangtong.entity.QMember;
import com.example.sijangtong.entity.QOrder;
import com.example.sijangtong.entity.QOrderItem;
import com.example.sijangtong.entity.QProduct;
import com.example.sijangtong.entity.QRider;
import com.example.sijangtong.entity.QStore;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

public class OrderOrderItemMemberRiderProductRepositoryImpl extends QuerydslRepositorySupport
        implements OrderOrderItemMemberRiderProductRepository {

    public OrderOrderItemMemberRiderProductRepositoryImpl() {
        super(Order.class);

    }

    @Override
    public Page<Object[]> getOrderList(Pageable pageable, Long storeId) {
        // SELECT *
        // FROM ORDERS o
        // LEFT JOIN ORDER_ITEM oi ON o.ORDER_ID = oi.ORDER_ORDER_ID
        // LEFT JOIN RIDER r ON r.RIDER_ID = o.rider_rider_id
        // LEFT JOIN "MEMBER" m ON m.MEMBER_EMAIL = o.MEMBER_MEMBER_EMAIL
        // LEFT JOIN PRODUCT p ON p.PRODUCT_ID = oi.PRODUCT_PRODUCT_ID
        // LEFT JOIN STORE s ON s.STORE_ID = o.store_store_id
        // WHERE o.store_store_id = 84;

        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        QRider rider = QRider.rider;
        QMember member = QMember.member;
        QProduct product = QProduct.product;
        QStore store = QStore.store;

        JPQLQuery<Order> query = from(order);
        query.leftJoin(orderItem).on(orderItem.order.eq(order));
        query.leftJoin(rider).on(order.rider.eq(rider));
        query.leftJoin(member).on(order.member.eq(member));
        query.leftJoin(product).on(orderItem.product.eq(product));
        query.leftJoin(store).on(order.store.eq(store));

        JPQLQuery<Tuple> tuple = query.select(order, orderItem, rider, member, product, store)
                .where(order.store.storeId.eq(storeId));

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()));
    }

}
