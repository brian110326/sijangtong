package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInfo is a Querydsl query type for Info
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInfo extends EntityPathBase<Info> {

    private static final long serialVersionUID = -978546154L;

    public static final QInfo info = new QInfo("info");

    public final StringPath storeAddress = createString("storeAddress");

    public final StringPath storeDetail = createString("storeDetail");

    public final NumberPath<Long> storeInfoId = createNumber("storeInfoId", Long.class);

    public final StringPath storeName = createString("storeName");

    public final StringPath storeTel = createString("storeTel");

    public final StringPath storeTime = createString("storeTime");

    public QInfo(String variable) {
        super(Info.class, forVariable(variable));
    }

    public QInfo(Path<? extends Info> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInfo(PathMetadata metadata) {
        super(Info.class, metadata);
    }

}

