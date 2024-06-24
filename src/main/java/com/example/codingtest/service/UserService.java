package com.example.codingtest.service;

import com.example.codingtest.dto.UserDto;
import com.example.codingtest.entity.User;
import com.example.codingtest.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.example.codingtest.entity.QUser.user;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final JPAQueryFactory queryFactory;
    private final UserRepository userRepository;

    @Value("${add_test_year}")
    int addTestYear;

    @Value("${add_test_month}")
    int addTestMonth;

    @Value("${add_test_day}")
    int addTestDay;

    @Value("${add_test_hour_start}")
    int addTestHourStart;

    @Value("${add_test_hour_end}")
    int addTestHourEnd;

    public Optional<User> findBySeq(Integer userSeq) {
        return userRepository.findById(userSeq);
    }

    public User findByUserId(String userId){
        return queryFactory.selectFrom(user).where(user.userId.eq(userId)).fetchOne();
    }

    public Integer loginCheck(UserDto dto){
        User uu = queryFactory.selectFrom(user).where(user.userId.eq(dto.getUserId())).fetchOne();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime addTestStart = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourStart,0,0);
        LocalDateTime addTestEnd = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourEnd,0,0);

        //계정이 없는 경우
        if(uu==null) return 0;

        //이미 응시한 경우
        if(uu.getUserSubmitDt()!=null) return -3;

        //관리자 계정으로 로그인한 경우
        if("adminSCH".equals(uu.getUserId())) return -4;

        //비밀번호가 틀렸을 경우
        if(!uu.getUserPassword().equals(dto.getUserPassword())) return -1;

        //예비 일정 시간일 경우
        if(addTestStart.isBefore(now) && addTestEnd.isAfter(now)) return uu.getUserSeq();

        //응시 일자 내에 접속하지 않은 경우
        if(!uu.getUserTestStart().isBefore(now)) return -2;
        if(!uu.getUserTestEnd().isAfter(now)) return -2;

        //정상 접속한 경우
        return uu.getUserSeq();
    }

    public void setLoginDt(Integer userSeq){
        queryFactory.update(user)
                .set(user.userLoginDt, LocalDateTime.now())
                .where(user.userSeq.eq(userSeq))
                .execute();
    }

    public LocalDateTime timerFinishTime(Integer userSeq) {
        LocalDateTime addTestStart = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourStart,0,0);
        LocalDateTime addTestEnd = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourEnd,0,0);

        queryFactory.update(user)
                .set(user.userTestStart, addTestStart)
                .set(user.userTestEnd, addTestEnd)
                .where(user.userSeq.eq(userSeq))
                .execute();

        return addTestEnd;
    }

    public boolean isFinishTime(LocalDateTime now){
        LocalDateTime addTestStart = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourStart,0,0);
        LocalDateTime addTestEnd = LocalDateTime.of(addTestYear,addTestMonth,addTestDay,addTestHourEnd,0,0);

        if(addTestStart.isBefore(now) && addTestEnd.isAfter(now))
            return true;
        else
            return false;
    }

    public boolean checkSubmitDt(User uu){
        User u = queryFactory.selectFrom(user).where(user.eq(uu)).fetchOne();
        if(u.getUserSubmitDt() == null) return false;

        return true;
    }

    public void schUserInfo(MultipartFile file) throws IOException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int startCell = 1;

        for(int i=startCell; i<sheet.getPhysicalNumberOfRows(); i++){
            Row row = sheet.getRow(i);
            if(row == null) continue;

            String userLevel = String.valueOf(row.getCell(6).getStringCellValue().charAt(3));
            LocalDateTime userTestStart = null;
            LocalDateTime userTestEnd = null;

            switch (userLevel){
                case "1":
                    userTestStart = LocalDateTime.parse("2023-11-23 09:00:00", f);
                    userTestEnd = LocalDateTime.parse("2023-11-23 10:00:00", f);
                    break;
                case "2":
                    userTestStart = LocalDateTime.parse("2023-11-24 09:00:00", f);
                    userTestEnd = LocalDateTime.parse("2023-11-24 10:00:00", f);
                    break;
                case "3":
                    userTestStart = LocalDateTime.parse("2023-11-25 09:00:00", f);
                    userTestEnd = LocalDateTime.parse("2023-11-25 10:00:00", f);
                    break;
            }

            String pw = "";
            if(row.getCell(5).getCellType()== CellType.NUMERIC) {
                pw = String.valueOf((int)row.getCell(5).getNumericCellValue());
            } else {
                pw = row.getCell(5).getStringCellValue();
            }

            User user = User.builder()
                    .userId(String.valueOf((int)row.getCell(3).getNumericCellValue()))
                    .userPassword(pw)
                    .userName(row.getCell(4).getStringCellValue())
                    .userMajor(row.getCell(1).getStringCellValue())
                    .userLevel(userLevel)
                    .userTestStart(userTestStart)
                    .userTestEnd(userTestEnd)
                    .build();

            userRepository.save(user);
            log.info(user.toString());
        }
    }
}