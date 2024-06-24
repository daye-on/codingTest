package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TBL_SQ_RESULT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SQResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sq_result_seq")
    private Integer sqResultSeq;

    @Column(name = "sq_result")
    private String sqResult;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Builder
    public SQResult(Integer sqResultSeq, String sqResult, User user) {
        this.sqResultSeq = sqResultSeq;
        this.sqResult = sqResult;
        this.user = user;
    }
}
