package com.example.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    // 1일(밀리초). 실제 운영시에는 더 짧은게 낫다.
    static final long EXPIRATINTIME = 86400000;
    static final String PREFIX = "Bearer";

    // 비밀키 생성.
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // HS 잘 확인하기

    // 서명이 이루어진 JWT 토큰을 생성
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATINTIME)) // 현재 시간 + 24시간 후
                .signWith(key)          // 생성한 비밀키로 서명
                .compact();
        return token;
    }
    // 요청(Request)의 Authorization 헤더에서 토큰을 가져온 뒤에 그 토큰 내부를 확인하고,
    // username을 가지고 오는 부분이다.
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(     // 이 클래스의 객체가 정확히 뭔지는 모르겠지만
                // method 명을 봤을 때 Header을 가지고 온다는 것을 알 수 있다.
                // 여기 Header는 postman에서 볼 수 있는 headers에 해당한다.
                HttpHeaders.AUTHORIZATION
        );
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return user;
            }
        }
        return null;
    }
}
