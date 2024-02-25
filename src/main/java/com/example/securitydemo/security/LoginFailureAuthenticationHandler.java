package com.example.securitydemo.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 13:12
 **/
public class LoginFailureAuthenticationHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String localizedMessage = exception.getLocalizedMessage();
        Map result = new HashMap();
        result.put("code", -1);
        result.put("message", localizedMessage == null ? "登录失败" : localizedMessage);
        String json = JSONUtil.toJsonStr(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
