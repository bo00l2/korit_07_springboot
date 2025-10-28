package com.shoppinglist2.shoppinglist2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// JwtService와 UserDetailsServiceImpl을 주입 받을 거다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // header에서 사용자 이름(username)을 추출
        String username = jwtService.getAuthUser(request); // 성공하면 user return

        // 사용자가 존재하고, Spring Security Context에 인증 정보가 없다면
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // DB에서 user 정보 조회
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 토큰이 유효하면 인증 토큰을 생성하여 SecurityContext에 등록
            // 근데 잘 생각해보면 getAuthUser()에서 검증이 끝남.
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // cardatabase랑 비교할 필요가 있음
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
