package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String header = request.getHeader(HEADER_AUTH);
        System.out.println("Authorization Header: " + header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("Extracted Token: " + token);
            try {
                jwtUtil.vaildate(token);
                System.out.println("Token validation successful");
                return true;
            } catch (Exception e) {
                System.out.println("Token validation failed: " + e.getMessage());
                throw new Exception("토큰 검증 실패: " + e.getMessage());
            }
        }

        System.out.println("Missing or malformed token");
        throw new Exception("유효하지 않은 접근입니다. 토큰이 제공되지 않았거나 형식이 잘못되었습니다.");
    }
}
