package com.example.securitydemo.security;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 13:55
 **/
public class SessionInformationExpiredStrategyHandler implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Map result = new HashMap();
        result.put("code", -1);
        result.put("message", "账号已再其他终端登录");
        String json = JSONUtil.toJsonStr(result);
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
