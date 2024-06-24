package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPQResult is a Querydsl query type for PQResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPQResult extends EntityPathBase<PQResult> {

    private static final long serialVersionUID = 482818760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPQResult pQResult = new QPQResult("pQResult");

    public final StringPath pqResult = createString("pqResult");

    public final NumberPath<Integer> pqResultSeq = createNumber("pqResultSeq", Integer.class);

    public final QPQuestion pQuestion;

    public final QUser user;

    public QPQResult(String variable) {
        this(PQResult.class, forVariable(variable), INITS);
    }

    public QPQResult(Path<? extends PQResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPQResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPQResult(PathMetadata metadata, PathInits inits) {
        this(PQResult.class, metadata, inits);
    }

    public QPQResult(Class<? extends PQResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pQuestion = inits.isInitialized("pQuestion") ? new QPQuestion(forProperty("pQuestion")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

