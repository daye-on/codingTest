package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMCQuestion is a Querydsl query type for MCQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMCQuestion extends EntityPathBase<MCQuestion> {

    private static final long serialVersionUID = -573617562L;

    public static final QMCQuestion mCQuestion = new QMCQuestion("mCQuestion");

    public final NumberPath<Integer> mcqAnswer = createNumber("mcqAnswer", Integer.class);

    public final StringPath mcqImg = createString("mcqImg");

    public final StringPath mcqLevel = createString("mcqLevel");

    public final StringPath mcqOption1 = createString("mcqOption1");

    public final StringPath mcqOption2 = createString("mcqOption2");

    public final StringPath mcqOption3 = createString("mcqOption3");

    public final StringPath mcqOption4 = createString("mcqOption4");

    public final StringPath mcqOption5 = createString("mcqOption5");

    public final StringPath mcqQuestion = createString("mcqQuestion");

    public final NumberPath<Integer> mcqSeq = createNumber("mcqSeq", Integer.class);

    public QMCQuestion(String variable) {
        super(MCQuestion.class, forVariable(variable));
    }

    public QMCQuestion(Path<? extends MCQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMCQuestion(PathMetadata metadata) {
        super(MCQuestion.class, metadata);
    }

}

