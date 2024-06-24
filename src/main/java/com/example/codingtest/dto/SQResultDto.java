package com.example.codingtest.dto;

import lombok.Data;

@Data
public class SQResultDto {
    private Integer sqResultSeq;
    private String sqResult;
    private Integer userSeq;
    private String sqQuestion;
    private String sqAnswer;
    private Integer sqQuestionSeq;
}
