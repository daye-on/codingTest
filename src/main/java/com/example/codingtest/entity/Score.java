package com.example.codingtest.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TBL_SCORE")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_seq")
    private Integer scoreSeq;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(name = "score_pq")
    private Integer scorePq;

    @Column(name = "score_sq")
    private Integer scoreSq;

    @Column(name = "score_mcq")
    private Integer scoreMcq;

    @Column(name = "score_all")
    private Integer scoreAll;

    @Builder
    public Score(Integer scoreSeq, User user, Integer scorePq, Integer scoreSq, Integer scoreMcq, Integer scoreAll) {
        this.scoreSeq = scoreSeq;
        this.user = user;
        this.scorePq = scorePq;
        this.scoreSq = scoreSq;
        this.scoreMcq = scoreMcq;
        this.scoreAll = scoreAll;
    }
}
