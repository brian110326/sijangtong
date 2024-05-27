package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -264501530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QMember member;

    public final StringPath orderAddress = createString("orderAddress");

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final QOrderItem orderItems;

    public final EnumPath<com.example.sijangtong.constant.OrderPayment> orderPayment = createEnum("orderPayment", com.example.sijangtong.constant.OrderPayment.class);

    public final QRider rider;

    public final EnumPath<com.example.sijangtong.constant.RiderOrdercancel> riderOrdercancel = createEnum("riderOrdercancel", com.example.sijangtong.constant.RiderOrdercancel.class);

    public final QStore store;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.orderItems = inits.isInitialized("orderItems") ? new QOrderItem(forProperty("orderItems"), inits.get("orderItems")) : null;
        this.rider = inits.isInitialized("rider") ? new QRider(forProperty("rider"), inits.get("rider")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

