package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1411492299L;

    public static final QUser user = new QUser("user");

    public final StringPath userId = createString("userId");

    public final StringPath userLevel = createString("userLevel");

    public final DateTimePath<java.time.LocalDateTime> userLoginDt = createDateTime("userLoginDt", java.time.LocalDateTime.class);

    public final StringPath userMajor = createString("userMajor");

    public final StringPath userName = createString("userName");

    public final StringPath userPassword = createString("userPassword");

    public final NumberPath<Integer> userSeq = createNumber("userSeq", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> userSubmitDt = createDateTime("userSubmitDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> userTestEnd = createDateTime("userTestEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> userTestStart = createDateTime("userTestStart", java.time.LocalDateTime.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

