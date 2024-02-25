package com.example.securitydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.securitydemo.pojo.LoginUser;

/**
 * @author kw
 * @program security-demo
 * @description
 * @create 2024 - 02 - 25 11:10
 **/
public interface UserService extends IService<LoginUser> {
    void saveUserDetails(LoginUser loginUser);
}
