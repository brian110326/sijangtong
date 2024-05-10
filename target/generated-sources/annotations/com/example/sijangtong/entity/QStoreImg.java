package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreImg is a Querydsl query type for StoreImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreImg extends EntityPathBase<StoreImg> {

    private static final long serialVersionUID = 1982834826L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreImg storeImg = new QStoreImg("storeImg");

    public final StringPath stImgName = createString("stImgName");

    public final QStore store;

    public final NumberPath<Long> storeImgId = createNumber("storeImgId", Long.class);

    public final StringPath stPath = createString("stPath");

    public final StringPath stUuid = createString("stUuid");

    public QStoreImg(String variable) {
        this(StoreImg.class, forVariable(variable), INITS);
    }

    public QStoreImg(Path<? extends StoreImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreImg(PathMetadata metadata, PathInits inits) {
        this(StoreImg.class, metadata, inits);
    }

    public QStoreImg(Class<? extends StoreImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

