package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPQuestion is a Querydsl query type for PQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPQuestion extends EntityPathBase<PQuestion> {

    private static final long serialVersionUID = -1214662580L;

    public static final QPQuestion pQuestion = new QPQuestion("pQuestion");

    public final StringPath pqComment1 = createString("pqComment1");

    public final StringPath pqComment2 = createString("pqComment2");

    public final StringPath pqExInput = createString("pqExInput");

    public final StringPath pqExOutput = createString("pqExOutput");

    public final StringPath pqImg = createString("pqImg");

    public final StringPath pqLevel = createString("pqLevel");

    public final StringPath pqQuestion = createString("pqQuestion");

    public final NumberPath<Integer> pqSeq = createNumber("pqSeq", Integer.class);

    public QPQuestion(String variable) {
        super(PQuestion.class, forVariable(variable));
    }

    public QPQuestion(Path<? extends PQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPQuestion(PathMetadata metadata) {
        super(PQuestion.class, metadata);
    }

}

