package com.example.sijangtong.repository.memberTotal;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.entity.Member;
import com.example.sijangtong.entity.QMember;
import com.example.sijangtong.entity.QOrder;
import com.example.sijangtong.entity.QOrderItem;
import com.example.sijangtong.entity.QProduct;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class MemberReviewRepositoryImpl extends QuerydslRepositorySupport implements MemberReviewRepository {

    public MemberReviewRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> writableMembers(Long productId) {
        // SELECT *
        // FROM "MEMBER" m
        // WHERE m.MEMBER_EMAIL IN (SELECT o.MEMBER_MEMBER_EMAIL
        // FROM ORDERS o
        // JOIN ORDER_ITEM oi ON o.ORDER_ID = oi.ORDER_ORDER_ID
        // JOIN "MEMBER" m ON m.MEMBER_EMAIL = o.MEMBER_MEMBER_EMAIL
        // WHERE oi.PRODUCT_PRODUCT_ID = 60

        // 특정 제품을 구매한 member만 reivew를 달 수 있음
        // review를 달 수 있는 member의 list를 뽑아오기

        QMember member = QMember.member;
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        QProduct product = QProduct.product;

        JPQLQuery<Member> query = from(member);

        JPQLQuery<Member> tuple = query.select(member).where(
                member.memberEmail.in((JPAExpressions.select(order.member.memberEmail).from(order).join(orderItem)
                        .on(orderItem.order.eq(order)).join(member).on(order.member.eq(member))
                        .where(orderItem.product.productId.eq(productId)))));

        List<Member> members = tuple.fetch();

        return members;
    }

}
