package com.example.codingtest.dto;

import lombok.Data;

@Data
public class SQDto {
    private Integer sqSeq;
    private String sqQuestion;
    private String sqComment;
    private String sqAnswer;
    private String sqLevel;
    private String sqImg;
}