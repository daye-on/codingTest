package com.example.codingtest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSQuestion is a Querydsl query type for SQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSQuestion extends EntityPathBase<SQuestion> {

    private static final long serialVersionUID = 1952908623L;

    public static final QSQuestion sQuestion = new QSQuestion("sQuestion");

    public final StringPath sqAnswer = createString("sqAnswer");

    public final StringPath sqComment = createString("sqComment");

    public final StringPath sqImg = createString("sqImg");

    public final StringPath sqLevel = createString("sqLevel");

    public final StringPath sqQuestion = createString("sqQuestion");

    public final NumberPath<Integer> sqSeq = createNumber("sqSeq", Integer.class);

    public QSQuestion(String variable) {
        super(SQuestion.class, forVariable(variable));
    }

    public QSQuestion(Path<? extends SQuestion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSQuestion(PathMetadata metadata) {
        super(SQuestion.class, metadata);
    }

}

