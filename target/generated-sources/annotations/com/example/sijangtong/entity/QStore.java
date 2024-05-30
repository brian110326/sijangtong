package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -260736903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final StringPath closeTime = createString("closeTime");

    public final QMember member;

    public final StringPath openTime = createString("openTime");

    public final StringPath storeAddress = createString("storeAddress");

    public final EnumPath<com.example.sijangtong.constant.StoreCategory> storeCategory = createEnum("storeCategory", com.example.sijangtong.constant.StoreCategory.class);

    public final StringPath storeDetail = createString("storeDetail");

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public final StringPath storeName = createString("storeName");

    public final StringPath storeTel = createString("storeTel");

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

