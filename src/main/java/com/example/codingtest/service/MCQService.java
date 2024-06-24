package com.example.codingtest.service;

import com.example.codingtest.dto.MCQResultDto;
import com.example.codingtest.entity.MCQResult;
import com.example.codingtest.entity.MCQuestion;
import com.example.codingtest.entity.User;
import com.example.codingtest.repository.MCQResultRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.codingtest.entity.QMCQuestion.mCQuestion;
import static com.example.codingtest.entity.QUser.user;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MCQService {
    private final JPAQueryFactory queryFactory;
    private final MCQResultRepository mcqResultRepository;
    private final UserService userService;

    public List<MCQuestion> findByLevel(String level) {

        // 객관식 문제
        // 레벨1-4문항, 레벨2-5문항, 레벨3-2문항

        int count = 4;
        if("2".equals(level)) count = 5;
        if("3".equals(level)) count = 2;

        return queryFactory.selectFrom(mCQuestion)
                .where(mCQuestion.mcqLevel.eq(level))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    public List<MCQuestion> findByLevelAll(String level) {
        return queryFactory.selectFrom(mCQuestion)
                .where(mCQuestion.mcqLevel.eq(level))
                .fetch();
    }

    public void insertResult(MCQResultDto dto) {
        User uu = userService.findBySeq(dto.getUserSeq()).get();
        MCQResult result = MCQResult.builder()
                .mcqResult(dto.getMcqResult())
                .user(uu)
                .mcqResultScore(dto.getMcqResultScore())
                .build();

        mcqResultRepository.save(result);

        queryFactory.update(user)
                .set(user.userSubmitDt, LocalDateTime.now())
                .where(user.userSeq.eq(uu.getUserSeq()))
                .execute();
    }

    public Integer setResultScore(MCQResultDto dto, User uu) {
        String level = uu.getUserLevel();
        List<MCQuestion> qList = findByLevelAll(level);
        List<String> result = new ArrayList<>();
        int finalScore = 0;

        for (MCQuestion mcq : qList) {
            String rString = "\"" + mcq.getMcqSeq() + "\":\"" + mcq.getMcqAnswer() + "\"";
            result.add(rString);
        }

        for (String score : result) {
            if (dto.getMcqResult().contains(score)) {
                finalScore++;
            }
        }

        return finalScore;
    }
}