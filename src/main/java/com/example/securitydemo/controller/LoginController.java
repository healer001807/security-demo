package com.example.securitydemo.controller;

import com.example.securitydemo.pojo.LoginUser;
import com.example.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 11:15
 **/
@Controller
@RequestMapping("login")
public class LoginController {


    @GetMapping
    public String login() {
        return "login";
    }
}
