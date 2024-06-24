package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TBL_MCQ_RESULT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MCQResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcq_result_seq")
    private Integer mcqResultSeq;

    @Column(name = "mcq_result")
    private String mcqResult;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(name = "mcq_result_score")
    private Integer mcqResultScore;

    @Builder
    public MCQResult(Integer mcqResultSeq, String mcqResult, User user, Integer mcqResultScore) {
        this.mcqResultSeq = mcqResultSeq;
        this.mcqResult = mcqResult;
        this.user = user;
        this.mcqResultScore = mcqResultScore;
    }
}