package com.example.securitydemo.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
public class LoginSuccessAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 用户身份信息
        Object principal = authentication.getPrincipal();
        // 用户凭证信息
        Object credentials = authentication.getCredentials();
        // 用户权限信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Map result = new HashMap();
        result.put("code", 0);
        result.put("message", "登录成功");
        result.put("data", principal);
        String json = JSONUtil.toJsonStr(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
