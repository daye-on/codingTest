package com.example.codingtest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(request.getRequestURI().contains("favicon")) return true;
        if(request.getRequestURI().contains("sessionConsole")) return true;

        if (session == null || session.getAttribute("user") == null) {
            log.info("[Interceptor] 미인증 사용자 요청 : "+request.getRequestURI());
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
