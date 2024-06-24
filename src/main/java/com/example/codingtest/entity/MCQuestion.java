package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TBL_MCQ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MCQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcq_seq")
    private Integer mcqSeq;

    @Column(name = "mcq_question")
    private String mcqQuestion;

    @Column(name = "mcq_answer")
    private Integer mcqAnswer;

    @Column(name = "mcq_option1")
    private String mcqOption1;

    @Column(name = "mcq_option2")
    private String mcqOption2;

    @Column(name = "mcq_option3")
    private String mcqOption3;

    @Column(name = "mcq_option4")
    private String mcqOption4;

    @Column(name = "mcq_option5")
    private String mcqOption5;

    @Column(name = "mcq_level")
    private String mcqLevel;

    @Column(name = "mcq_img")
    private String mcqImg;
}
