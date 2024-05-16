package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.Path;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;

/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -260736903L;

    public static final QStore store = new QStore("store");

    public final StringPath closeTime = createString("closeTime");

    public final StringPath openTime = createString("openTime");

    public final StringPath storeAddress = createString("storeAddress");

    public final EnumPath<com.example.sijangtong.constant.StoreCategory> storeCategory = createEnum("storeCategory",
            com.example.sijangtong.constant.StoreCategory.class);

    public final StringPath storeDetail = createString("storeDetail");

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public final StringPath storeName = createString("storeName");

    public final StringPath storeTel = createString("storeTel");

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }
}
