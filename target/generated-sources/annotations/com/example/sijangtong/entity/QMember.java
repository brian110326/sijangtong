package com.example.sijangtong.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 321388034L;

    public static final QMember member = new QMember("member1");

    public final StringPath memberAddress = createString("memberAddress");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickname = createString("memberNickname");

    public final StringPath memberPwd = createString("memberPwd");

    public final EnumPath<com.example.sijangtong.constant.MemberRole> memberRole = createEnum("memberRole", com.example.sijangtong.constant.MemberRole.class);

    public final StringPath memberTell = createString("memberTell");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

