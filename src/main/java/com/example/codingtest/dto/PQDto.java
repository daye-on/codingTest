package com.example.codingtest.dto;

import com.example.codingtest.entity.PQuestion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PQDto {
    private Integer pqSeq;
    private String pqQuestion;
    private String pqLevel;
    private String pqComment1;
    private String pqComment2;
    private String pqExInput;
    private String pqExOutput;
    private String savedResult;
    private String pqImg;

    public static PQDto toDTO(PQuestion e, String result) {
        return PQDto.builder()
                .pqSeq(e.getPqSeq())
                .pqQuestion(e.getPqQuestion())
                .pqLevel(e.getPqLevel())
                .pqComment1(e.getPqComment1())
                .pqComment2(e.getPqComment2())
                .pqExInput(e.getPqExInput())
                .pqExOutput(e.getPqExOutput())
                .pqImg(e.getPqImg())
                .savedResult(result)
                .build();
    }
}
