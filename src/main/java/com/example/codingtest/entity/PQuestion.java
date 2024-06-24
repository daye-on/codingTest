package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TBL_PQ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pq_seq")
    private Integer pqSeq;

    @Column(name = "pq_question")
    private String pqQuestion;

    @Column(name = "pq_level")
    private String pqLevel;

    @Column(name = "pq_comment1")
    private String pqComment1;

    @Column(name = "pq_comment2")
    private String pqComment2;

    @Column(name = "pq_ex_input")
    private String pqExInput;

    @Column(name = "pq_ex_output")
    private String pqExOutput;

    @Column(name = "pq_img")
    private String pqImg;

    public PQuestion(Integer pqSeq){
        this.pqSeq = pqSeq;
    }
}