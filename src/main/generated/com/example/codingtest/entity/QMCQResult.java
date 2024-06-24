package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMCQResult is a Querydsl query type for MCQResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMCQResult extends EntityPathBase<MCQResult> {

    private static final long serialVersionUID = -1159070354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMCQResult mCQResult = new QMCQResult("mCQResult");

    public final StringPath mcqResult = createString("mcqResult");

    public final NumberPath<Integer> mcqResultScore = createNumber("mcqResultScore", Integer.class);

    public final NumberPath<Integer> mcqResultSeq = createNumber("mcqResultSeq", Integer.class);

    public final QUser user;

    public QMCQResult(String variable) {
        this(MCQResult.class, forVariable(variable), INITS);
    }

    public QMCQResult(Path<? extends MCQResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMCQResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMCQResult(PathMetadata metadata, PathInits inits) {
        this(MCQResult.class, metadata, inits);
    }

    public QMCQResult(Class<? extends MCQResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

