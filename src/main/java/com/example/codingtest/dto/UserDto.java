package com.example.codingtest.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private Integer userSeq;
    private String userId;
    private String userPassword;
    private String userName;
    private String userMajor;
    private String userLevel;
    private String userLoginDt;
    private String userSubmitDt;
    private String userTestStart;
    private String userTestEnd;
    private String userScoreAll;
    private String userMcqScore;
    private String userPqResult;
    private String userSqResult;

    @Builder
    public UserDto(Integer userSeq, String userId, String userPassword, String userName, String userMajor, String userLevel, String userLoginDt, String userSubmitDt, String userTestStart, String userTestEnd, String userScoreAll, String userMcqScore, String userPqResult, String userSqResult) {
        this.userSeq = userSeq;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userMajor = userMajor;
        this.userLevel = userLevel;
        this.userLoginDt = userLoginDt;
        this.userSubmitDt = userSubmitDt;
        this.userTestStart = userTestStart;
        this.userTestEnd = userTestEnd;
        this.userScoreAll = userScoreAll;
        this.userMcqScore = userMcqScore;
        this.userPqResult = userPqResult;
        this.userSqResult = userSqResult;
    }
}