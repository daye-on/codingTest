package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScore is a Querydsl query type for Score
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScore extends EntityPathBase<Score> {

    private static final long serialVersionUID = -808902296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScore score = new QScore("score");

    public final NumberPath<Integer> scoreAll = createNumber("scoreAll", Integer.class);

    public final NumberPath<Integer> scoreMcq = createNumber("scoreMcq", Integer.class);

    public final NumberPath<Integer> scorePq = createNumber("scorePq", Integer.class);

    public final NumberPath<Integer> scoreSeq = createNumber("scoreSeq", Integer.class);

    public final NumberPath<Integer> scoreSq = createNumber("scoreSq", Integer.class);

    public final QUser user;

    public QScore(String variable) {
        this(Score.class, forVariable(variable), INITS);
    }

    public QScore(Path<? extends Score> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScore(PathMetadata metadata, PathInits inits) {
        this(Score.class, metadata, inits);
    }

    public QScore(Class<? extends Score> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

