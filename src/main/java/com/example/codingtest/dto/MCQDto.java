package com.example.codingtest.dto;

import lombok.Data;

@Data
public class MCQDto {
    private Integer mcqSeq;
    private String mcqQuestion;
    private Integer mcqAnswer;
    private String mcqOption1;
    private String mcqOption2;
    private String mcqOption3;
    private String mcqOption4;
    private String mcqOption5;
    private String mcqLevel;
    private String mcqImg;
}
