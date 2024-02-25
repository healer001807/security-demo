package com.example.securitydemo.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 14:17
 **/
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map result = new HashMap();
        result.put("code", -1);
        result.put("message", "没有权限");
        String json = JSONUtil.toJsonStr(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
