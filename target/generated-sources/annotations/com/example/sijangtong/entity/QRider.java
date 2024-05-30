package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRider is a Querydsl query type for Rider
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRider extends EntityPathBase<Rider> {

    private static final long serialVersionUID = -261999086L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRider rider = new QRider("rider");

    public final QOrder order;

    public final NumberPath<Long> riderId = createNumber("riderId", Long.class);

    public final StringPath riderName = createString("riderName");

    public final EnumPath<com.example.sijangtong.constant.RiderStatus> riderStatus = createEnum("riderStatus", com.example.sijangtong.constant.RiderStatus.class);

    public final StringPath riderTel = createString("riderTel");

    public QRider(String variable) {
        this(Rider.class, forVariable(variable), INITS);
    }

    public QRider(Path<? extends Rider> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRider(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRider(PathMetadata metadata, PathInits inits) {
        this(Rider.class, metadata, inits);
    }

    public QRider(Class<? extends Rider> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

