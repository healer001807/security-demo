package com.example.securitydemo.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 24 22:02
 **/

@RestController
public class IndexController {

    @GetMapping
    public String index() {
        // 身份认证信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object credentials = authentication.getCredentials();
        Object principal = authentication.getPrincipal();

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("name", authentication.getName());
        jsonObject.set("authorities", authorities);
        Map result = new HashMap();
        result.put("code", 0);
        result.put("message", "");
        result.put("data", jsonObject);
        return JSONUtil.toJsonStr(result);
    }
}
