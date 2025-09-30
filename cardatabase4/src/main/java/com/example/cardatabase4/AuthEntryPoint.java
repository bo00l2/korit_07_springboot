package com.example.cardatabase4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {   // HTTP 응답 상태 코드 설정
    // 로그인 인증 실패 처리
    // 필터 체인에서 "인증이 안 된 사용자"가 보호된 리소스에 접근할 때 처리하는 로직
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 응답 상태 코드 401 로 지정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);  // 응답 컨텐츠 타입 application/json 으로 설정
        PrintWriter writer = response.getWriter();
        writer.println("Error : " + authException.getMessage());
    }
}
