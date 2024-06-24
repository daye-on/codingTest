package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSQResult is a Querydsl query type for SQResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSQResult extends EntityPathBase<SQResult> {

    private static final long serialVersionUID = 1416282469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSQResult sQResult = new QSQResult("sQResult");

    public final StringPath sqResult = createString("sqResult");

    public final NumberPath<Integer> sqResultSeq = createNumber("sqResultSeq", Integer.class);

    public final QUser user;

    public QSQResult(String variable) {
        this(SQResult.class, forVariable(variable), INITS);
    }

    public QSQResult(Path<? extends SQResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSQResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSQResult(PathMetadata metadata, PathInits inits) {
        this(SQResult.class, metadata, inits);
    }

    public QSQResult(Class<? extends SQResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

