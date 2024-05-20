package com.example.sijangtong.repository.riderAllocate;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.sijangtong.constant.RiderStatus;
import com.example.sijangtong.entity.QRider;
import com.example.sijangtong.entity.Rider;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

public class RiderRepositoryAllocateImpl extends QuerydslRepositorySupport implements RiderRepositoryAllocate {

    public RiderRepositoryAllocateImpl() {
        super(Rider.class);

    }

    @Override
    public Rider getRider() {
        // SELECT *
        // FROM RIDER r
        // WHERE r.RIDER_STATUS = 'WAITING'
        // AND r.RIDER_ID IN
        // (SELECT MIN(r2.rider_id) FROM RIDER r2 WHERE r.RIDER_STATUS = 'WAITING');

        QRider rider = QRider.rider;

        JPQLQuery<Rider> query = from(rider);

        JPQLQuery<Rider> allocation = query.select(rider).where(
                rider.riderStatus.eq(RiderStatus.WAITING)
                        .and(rider.riderId.in((JPAExpressions.select(rider.riderId.min()).from(rider)
                                .where(rider.riderStatus.eq(RiderStatus.WAITING))))));

        Rider allocatedRider = allocation.fetchOne();

        return allocatedRider;

    }

}
