package com.example.securitydemo.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 13:42
 **/
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String localizedMessage = authException.getLocalizedMessage();
        Map result = new HashMap();
        result.put("code", -1);
        result.put("message", localizedMessage);
        String json = JSONUtil.toJsonStr(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
