package com.example.codingtest.service;

import com.example.codingtest.dto.SQResultDto;
import com.example.codingtest.entity.SQResult;
import com.example.codingtest.entity.SQuestion;
import com.example.codingtest.entity.User;
import com.example.codingtest.repository.SQResultRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.codingtest.entity.QSQuestion.sQuestion;
import static com.example.codingtest.entity.QUser.user;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SQService {
    private final JPAQueryFactory queryFactory;
    private final UserService userService;
    private final SQResultRepository sqResultRepository;

    public List<SQuestion> findByLevel(String level) {

        // 주관식 문제
        // 레벨1-2문항, 레벨2-1문항, 레벨3-0문항

        int count = 2;
        if("2".equals(level)) count = 1;

        return queryFactory.selectFrom(sQuestion)
                .where(sQuestion.sqLevel.eq(level))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    public void insertResult(SQResultDto dto) {
        User uu = userService.findBySeq(dto.getUserSeq()).get();
        SQResult result = SQResult.builder()
                .sqResult(dto.getSqResult())
                .user(uu)
                .build();

        sqResultRepository.save(result);
        queryFactory.update(user)
                .set(user.userSubmitDt, LocalDateTime.now())
                .where(user.userSeq.eq(uu.getUserSeq()))
                .execute();
    }
}
