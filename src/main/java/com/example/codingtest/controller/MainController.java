package com.example.codingtest.controller;

import com.example.codingtest.dto.MCQResultDto;
import com.example.codingtest.dto.PQResultDto;
import com.example.codingtest.dto.SQResultDto;
import com.example.codingtest.dto.UserDto;
import com.example.codingtest.entity.User;
import com.example.codingtest.service.MCQService;
import com.example.codingtest.service.PQService;
import com.example.codingtest.service.SQService;
import com.example.codingtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MCQService mcqService;
    private final SQService sqService;
    private final PQService pqService;
    private final UserService userService;

    @GetMapping(value = "/")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "Login";
    }

    @PostMapping(value = "/login")
    public String loginCheck(@ModelAttribute UserDto dto, Model model, HttpServletRequest request) {

        // 로그인한 계정 정보으로 올바른 시험 응시인지 파악
        Integer resultSeq = userService.loginCheck(dto);
        HttpSession session = request.getSession();

        switch (resultSeq) {
            case 0 -> {
                log.info("[Login Fail] Not found : {}/{}", dto.getUserId(), dto.getUserPassword());
                model.addAttribute("error", "사용자 계정이 없습니다.");
                return "Login";
            }
            case -1 -> {
                log.info("[Login Fail] Incorrect password : {}/{}", dto.getUserId(), dto.getUserPassword());
                model.addAttribute("error", "비밀번호를 잘못 입력하였습니다.");
                return "Login";
            }
            case -2 -> {
                log.info("[Login Fail] Not test time : {}/{}", dto.getUserId(), dto.getUserPassword());
                model.addAttribute("error", "시험 응시 기간이 아닙니다.");
                return "Login";
            }
            case -3 -> {
                log.info("[Login Fail] Already submit : {}/{}", dto.getUserId(), dto.getUserPassword());
                model.addAttribute("error", "이미 응시한 시헙입니다.");
                return "Login";
            }
            case -4 -> {

                // 관리자 계정 : 채점 페이지 제공
                log.info("[Login Success - ADMIN] : {}/{}", dto.getUserId(), dto.getUserPassword());
                model.addAttribute("admin", "admin");
                session.setAttribute("user", "admin");
                return "Login";
            }
            default -> {

                // 정상적인 시험 응시. session 에 사용자 정보 저장.
                log.info("[Login Success] : {}", dto.getUserId());
                User user = userService.findBySeq(resultSeq).get();
                session.setAttribute("user", user);
                return "Notice";
            }
        }
    }

    @GetMapping(value = "/notice")
    public String onNotice(@SessionAttribute("user") User user) {
        return "redirect:main";
    }

    @GetMapping(value = "/main")
    public String main(Model model, @SessionAttribute("user") User user) {

        // 접속 일자를 DB에 저장.
        userService.setLoginDt(user.getUserSeq());

        // timer 의 역할 : DB에 미리 저장된 시험 종료 시간까지 남은 시간을 알려줌.
        // 예비 일정일 때, DB에 저장된 값이 아닌 application.properties 에 넣어둔 예비 일정 종료 시간으로 적용해야 함.
        LocalDateTime timer = user.getUserTestEnd();
        if(userService.isFinishTime(LocalDateTime.now()))
            timer = userService.timerFinishTime(user.getUserSeq());

        model.addAttribute("userInfo", user);
        model.addAttribute("timer", timer.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        model.addAttribute("question", mcqService.findByLevel(user.getUserLevel()));
        model.addAttribute("sQuestion", sqService.findByLevel(user.getUserLevel()));
        model.addAttribute("pQuestion", pqService.findByLevel(user));
        return "Main";
    }

    @PostMapping(value = "/savePQ_{seq}")
    public String savePQ(@PathVariable("seq") String seq, @SessionAttribute("user") User user, @ModelAttribute PQResultDto dto) {

        // 프로그래밍 문제 답안은 문제마다 '임시 저장' 기능을 제공함.
        String result = pqService.saveResult(dto, user);

        if("error".equals(result))
            log.info("[Save programming result ERROR] : {}", user.getUserId());

        return "Main";
    }

    @PostMapping(value = "/submitQuestion")
    public String submitQuestion(@SessionAttribute("user") User user
            , @ModelAttribute MCQResultDto dto
            , @ModelAttribute SQResultDto dto2
            , @ModelAttribute PQResultDto dto3) {

        // 객관식 문제 답안 저장
        dto.setUserSeq(user.getUserSeq());
        dto.setMcqResultScore(mcqService.setResultScore(dto, user));
        mcqService.insertResult(dto);

        // 주관식 문제 답안 저장
        dto2.setUserSeq(user.getUserSeq());
        sqService.insertResult(dto2);

        // 프로그래밍 문제 답안 저장
        PQResultDto pqrDto = new PQResultDto();
        String[] pqIndex = dto3.getPqResult().split(",,");
        ArrayList<String> result = new ArrayList<>();

        // 프로그래밍 문제 저장 시, 주관식처럼 한꺼번에 저장하는 것이 아니고 사용자가 풀이한 문제번호에 맞게 답안이 저장됨.
        for (int i = 0; i < pqIndex.length; i++) {
            int index = pqIndex[i].indexOf(":");
            pqrDto.setPqSeq(Integer.valueOf(pqIndex[i].substring(0, index)));
            pqrDto.setPqResult(pqIndex[i].substring(index + 1, pqIndex[i].length()));
            result.add(pqService.saveResult(pqrDto, user));
        }

        for(int i=0; i < result.size(); i++){
            if("error".equals(result.get(i)))
                log.info("[Save programming result ERROR] : {}", user.getUserId());
        }

        log.info("[Submit] : {}", user.getUserId());
        return "Main";
    }

    @ResponseBody
    @PostMapping(value = "/checkSubmitDt")
    public boolean checkSubmitDt(@SessionAttribute("user") User user){

        // 이미 답안을 제출하여 DB에 제출날짜가 있는 경우, 제출 못하도록 방지.
        return userService.checkSubmitDt(user);
    }

    @GetMapping(value = "/finish")
    public String finish(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "Finish";
    }

    @GetMapping(value = "/error")
    public String error() {
        return "Error";
    }

    @GetMapping(value = "/sessionConsole")
    public String sessionConsole(){

        // 현재는 사용 하지 않음
        // 세션 타임아웃 시간을 따로 지정하지 않아, 시험 도중에 세션(사용자 계정 정보 없어짐)이 풀려서 저장이 잘 되지 않았음.
        // 1분마다 컨트롤러 호출하여 세션 유지
        return "Main";
    }
}