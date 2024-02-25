package com.example.securitydemo.controller;

import com.example.securitydemo.pojo.LoginUser;
import com.example.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 11:15
 **/
@RestController
@RequestMapping("auth")
public class AuthenticationMethodController {

    @Resource
    private UserService userService;

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN') and authentication == 'admin'")
    public List<LoginUser> getList() {
        return userService.list();
    }

    @PostMapping("save")
    public void save(@RequestBody LoginUser loginUser) {
        userService.saveUserDetails(loginUser);
    }
}
