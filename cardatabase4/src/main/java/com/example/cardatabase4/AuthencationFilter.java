package com.example.cardatabase4;

import com.example.cardatabase4.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component // 클래스 전체를 빈으로 등록함
public class AuthencationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public AuthencationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 상속 받은 클래스의 HTTP 요청이 올 때마다 실행되는 메서드 doFilterInternal
        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jws != null){
            String user = jwtService.getAuthUser(request);  // JWT 토큰을 파싱해서 인증된 user 정보를 얻는다.

            // 인증 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            // credentials = null 인 이유 -> 이미 인증된 상태라서 / Collections.emptyList()는 빈 리스트, 권한이 없다는 의미
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 저장
        }
        filterChain.doFilter(request, response);    // 다음 필터로 요청 전달
    }
}
