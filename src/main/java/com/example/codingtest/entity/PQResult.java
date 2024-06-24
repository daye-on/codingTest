package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TBL_PQ_RESULT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PQResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pq_result_seq")
    private Integer pqResultSeq;

    @OneToOne
    @JoinColumn(name = "pq_seq")
    private PQuestion pQuestion;

    @Column(name = "pq_result")
    private String pqResult;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Builder
    public PQResult(Integer pqResultSeq, PQuestion pQuestion, String pqResult, User user) {
        this.pqResultSeq = pqResultSeq;
        this.pQuestion = pQuestion;
        this.pqResult = pqResult;
        this.user = user;
    }
}