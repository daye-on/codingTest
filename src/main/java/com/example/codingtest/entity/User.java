package com.example.codingtest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TBL_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Integer userSeq;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_major")
    private String userMajor;

    @Column(name = "user_level")
    private String userLevel;

    @Column(name = "user_login_dt")
    private LocalDateTime userLoginDt;

    @Column(name = "user_submit_dt")
    private LocalDateTime userSubmitDt;

    @Column(name = "user_test_start")
    private LocalDateTime userTestStart;

    @Column(name = "user_test_end")
    private LocalDateTime userTestEnd;

    @Builder
    public User(String userId, String userPassword, String userName, String userMajor, String userLevel, LocalDateTime userTestStart, LocalDateTime userTestEnd) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userMajor = userMajor;
        this.userLevel = userLevel;
        this.userTestStart = userTestStart;
        this.userTestEnd = userTestEnd;
    }
}